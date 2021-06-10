package victor.training.samples.two;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.concurrent.Future;

public class Sample2 {
   private ExAnteAsyncBABean exAnteAsyncBABean;
   private RequestReportBABean requestReportBABean;
   private CreateReportXmlBABean createReportXmlBABean;
   private BuildDocumentBABean buildDocumentBABean;

   @NotLoggable
   public String createExAnteCostOverviewReport(List<ExAnteCostOverviewReportRequest> requestList) {
      if (requestList == null || requestList.isEmpty()) {
         return null;
      }

      List<byte[]> detailedReports = new ArrayList<>();
      List<byte[]> aggregatedReports = new ArrayList<>();
      byte[] disclaimerPdf = getDisclaimerPdf(requestList);

      List<Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>>> futureTasks = new ArrayList<>();

//      requestList.stream().filter(ExAnteCostOverviewReportRequest::isCostOverview)
      hack(requestList);


      List<Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport>> responsePairs = extractOverviewReports(requestList, futureTasks);


      Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> detailedMap = new HashMap<>();
      // TODO
//      List<ReportWithXml {request, report, xml}>
      Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> aggregatedMap = new HashMap<>();

      int reportsGenerated = 0;
      for (Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport> pair : responsePairs) {
         reportsGenerated++;
         ExAnteCostOverviewReportRequest request = pair.getFirst();
         ExAnteCostOverviewReport report = pair.getSecond();

         Report aggregatedReport = createAggregateReport(request, report);
         aggregatedMap.put(new Pair<>(request, aggregatedReport), aggregatedReport.getXml());

         // Detailed
         String detailedReportId = getDetailedReportId(aggregatedReport.getReportId());
         Report detailedReport = createDetailedReport(request, report, detailedReportId);

         detailedMap.put(new Pair<>(request, detailedReport), detailedReport.getXml());
      }

      if (reportsGenerated == 0) {
         return null;
      }

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> detailedTasks = new ArrayList<>();
      createTasksForPdfCreation(detailedMap, detailedTasks);

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> aggregatedTask = new ArrayList<>();
      createTasksForPdfCreation(aggregatedMap, aggregatedTask);

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, byte[]>> detailedPdfResponsePairs = extractByteFromAsyncResult(
          detailedTasks);

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, byte[]>> aggregatedPdfResponsePairs = extractByteFromAsyncResult(
          aggregatedTask);

      sortRequestTaskList(detailedPdfResponsePairs);

      sortRequestTaskList(aggregatedPdfResponsePairs);

      for (Pair<Pair<ExAnteCostOverviewReportRequest, Report>, byte[]> detailedPdfResponse : detailedPdfResponsePairs) {
         for (Pair<Pair<ExAnteCostOverviewReportRequest, Report>, byte[]> aggregatedPdfResponse : aggregatedPdfResponsePairs) {
            if (StringUtils.equals(detailedPdfResponse.getFirst().getFirst().getIsin(),
                aggregatedPdfResponse.getFirst().getFirst().getIsin())) {
               byte[] aggregatedWithDisclaimerPdf = buildDocumentBABean
                   .mergePdfs(new byte[][] { aggregatedPdfResponse.getSecond(), disclaimerPdf }, true);

               saveOrUpdateAndPostProcessReport(aggregatedPdfResponse.getFirst().getSecond(),
                   aggregatedWithDisclaimerPdf, aggregatedPdfResponse.getFirst().getFirst());
               aggregatedReports.add(aggregatedPdfResponse.getSecond());

               byte[] detailedWithDisclaimerPdf = buildDocumentBABean
                   .mergePdfs(new byte[][] { detailedPdfResponse.getSecond(), disclaimerPdf }, true);

               saveOrUpdateAndPostProcessReport(detailedPdfResponse.getFirst().getSecond(),
                   detailedWithDisclaimerPdf, detailedPdfResponse.getFirst().getFirst());
               detailedReports.add(detailedPdfResponse.getSecond());
            }
         }
      }

      ExAnteCostOverviewReportRequest decePrima = requestList.get(0);

      Report aggregatedMergedReport = requestReportBABean.createInitialReportForNdg(
          decePrima.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT);

      Report detailedMergedReport = requestReportBABean.createInitialReportForNdgWithReportId(
          decePrima.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT,
          getDetailedReportId(aggregatedMergedReport.getReportId()));

      buildAndSaveMergedReport(aggregatedReports, disclaimerPdf, aggregatedMergedReport);

      buildAndSaveMergedReport(detailedReports, disclaimerPdf, detailedMergedReport);

      return aggregatedMergedReport.getReportId();
   }

   private String getDetailedReportId(String reportId) {
      return reportId + "_D";
   }

   private Report createDetailedReport(ExAnteCostOverviewReportRequest request, ExAnteCostOverviewReport report, String detailedReportId) {
      Report detailedReport = requestReportBABean.createInitialReportForNdgWithReportId(
          request.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT,
          detailedReportId);

      setReportNumberInExAnteCostReport(report, detailedReportId);
      setHeadlineTextForExAnte(report, false);
      String detailedReportXml = createReportXmlBABean.createReportXML(report);
      detailedReport.setXml(detailedReportXml);
      return detailedReport;
   }

   private Report createAggregateReport(ExAnteCostOverviewReportRequest request, ExAnteCostOverviewReport report) {
      // Aggregated
      Report aggregatedReport = requestReportBABean.createInitialReportForNdg(
          request.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT);

      setReportNumberInExAnteCostReport(report, aggregatedReport.getReportId());
      setHeadlineTextForExAnte(report, true);
      disableDetailedChapterInExAnteReport(report);
      String aggregatedReportXml = createReportXmlBABean.createReportXML(report);
      aggregatedReport.setXml(aggregatedReportXml);
      return aggregatedReport;
   }

   private List<Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport>> extractOverviewReports(List<ExAnteCostOverviewReportRequest> requestList, List<Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>>> futureTasks) {
      for (ExAnteCostOverviewReportRequest request : requestList) {
         if (!request.isCostOverview()) {
            continue;
         }

         Future<ExAnteCostOverviewReport> future = exAnteAsyncBABean.createAsyncExAnteCostOverviewReport(request);

         Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>> pair = new Pair<>(request, future);
         futureTasks.add(pair);
      }

      List<Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport>> responsePairs =
          extractResponsesFromAsyncResult(futureTasks);
//      responsePairs.removeIf(r -> r.getSecond() == null);


      for (Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport> pair : responsePairs) {
         if (pair.getSecond() == null) {
            throw new RuntimeException("AFARA!");
         }
      }
      return responsePairs;
   }

   private void hack(List<ExAnteCostOverviewReportRequest> requestList) {
      for (ExAnteCostOverviewReportRequest request : requestList) {
//         if (!request.getChapterSelection().contains(ExAnteCostChapterDefinition.COST_OVERVIEW)) {
//            continue;
//         }
//         if (request.getChapterSelection().contains(ExAnteCostChapterDefinition.COST_OVERVIEW)) {

         if (!request.isCostOverview()) {
            continue;
         }
         request.setChapterSelection(Arrays.asList(ExAnteCostChapterDefinition.COST_OVERVIEW)); // hack
      }
   }

   private void buildAndSaveMergedReport(List<byte[]> aggregatedReports, byte[] disclaimerPdf, Report aggregatedMergedReport) {
   }

   private void saveOrUpdateAndPostProcessReport(Report second, byte[] aggregatedWithDisclaimerPdf, ExAnteCostOverviewReportRequest first) {
   }

   private void sortRequestTaskList(List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>,byte[]>> detailedPdfResponsePairs) {
   }

   private List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, byte[]>> extractByteFromAsyncResult(List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> detailedTasks) {
      return null;
   }

   private void createTasksForPdfCreation(Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> detailedMap, List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> detailedTasks) {
   }

   private void disableDetailedChapterInExAnteReport(ExAnteCostOverviewReport exAnteCostReport) {
   }

   private void setHeadlineTextForExAnte(ExAnteCostOverviewReport exAnteCostReport, boolean b) {
   }

   private void setReportNumberInExAnteCostReport(ExAnteCostOverviewReport exAnteCostReport, String detailedReportId) {

   }

   private List<Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport>> extractResponsesFromAsyncResult(List<Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>>> futureTasks) {
      return null;
   }

   private byte[] getDisclaimerPdf(List<ExAnteCostOverviewReportRequest> exAnteCostOverviewReportRequestList) {

      return null;
   }
}
