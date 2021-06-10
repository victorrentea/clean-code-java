package victor.training.samples.two;

import org.apache.commons.lang.StringUtils;
import victor.training.samples.one.RequestContext;
import victor.training.samples.one.RequestContextThreadLocal;

import java.util.*;
import java.util.concurrent.Future;

public class Sample2 {
   private ExAnteAsyncBABean exAnteAsyncBABean;
   private RequestReportBABean requestReportBABean;
   private CreateReportXmlBABean createReportXmlBABean;
   private BuildDocumentBABean buildDocumentBABean;

   @NotLoggable
   public String createExAnteCostOverviewReport(
       List<ExAnteCostOverviewReportRequest> exAnteCostOverviewReportRequestList) {
      if (exAnteCostOverviewReportRequestList == null || exAnteCostOverviewReportRequestList.isEmpty()) {
         return null;
      }

      List<byte[]> detailedReports = new ArrayList<>();
      List<byte[]> aggregatedReports = new ArrayList<>();
      byte[] disclaimerPdf = getDisclaimerPdf(exAnteCostOverviewReportRequestList);

      RequestContext context = RequestContextThreadLocal.getRequestContext();

      List<Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>>> futureTasks = new ArrayList<>();

      for (ExAnteCostOverviewReportRequest exAnteCostOverviewReportRequest : exAnteCostOverviewReportRequestList) {
         if (!exAnteCostOverviewReportRequest.getChapterSelection().contains(ExAnteCostChapterDefinition.COST_OVERVIEW)) {
            continue;
         }
         exAnteCostOverviewReportRequest.setChapterSelection(Arrays.asList(ExAnteCostChapterDefinition.COST_OVERVIEW));

         Future<ExAnteCostOverviewReport> exAnteCostOverviewReportTask = exAnteAsyncBABean
             .createAsyncExAnteCostOverviewReport(context, exAnteCostOverviewReportRequest);

         Pair<ExAnteCostOverviewReportRequest, Future<ExAnteCostOverviewReport>> pair =
             new Pair<>(exAnteCostOverviewReportRequest, exAnteCostOverviewReportTask);
         futureTasks.add(pair);
      }

      List<Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport>> responsePairs = extractResponsesFromAsyncResult(
          futureTasks);

      int reportsGenerated = 0;

      Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> detailedMap = new HashMap<>();
      Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> aggregatedMap = new HashMap<>();

      for (Pair<ExAnteCostOverviewReportRequest, ExAnteCostOverviewReport> pair : responsePairs) {
         if (pair.getSecond() != null) {
            reportsGenerated++;
            ExAnteCostOverviewReportRequest exAnteCostOverviewReportRequest = pair.getFirst();

            ExAnteCostOverviewReport exAnteCostReport = pair.getSecond();

            Report aggregatedReport = requestReportBABean.createInitialReportForNdg(
                exAnteCostOverviewReportRequest.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT);

            String aggregatedReportId = aggregatedReport.getReportId();
            String detailedReportId = aggregatedReportId + "_D";

            Report detailedReport = requestReportBABean.createInitialReportForNdgWithReportId(
                exAnteCostOverviewReportRequest.getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT,
                detailedReportId);

            // Detailed
            setReportNumberInExAnteCostReport(exAnteCostReport, detailedReportId);
            setHeadlineTextForExAnte(exAnteCostReport, false);
            String detailedReportXml = createReportXmlBABean.createReportXML(exAnteCostReport);
            detailedReport.setXml(detailedReportXml);
            detailedMap.put(new Pair<>(exAnteCostOverviewReportRequest, detailedReport), detailedReportXml);

            // Aggregated
            setReportNumberInExAnteCostReport(exAnteCostReport, aggregatedReportId);
            setHeadlineTextForExAnte(exAnteCostReport, true);
            disableDetailedChapterInExAnteReport(exAnteCostReport);
            String aggregatedReportXml = createReportXmlBABean.createReportXML(exAnteCostReport);
            aggregatedReport.setXml(aggregatedReportXml);
            aggregatedMap.put(new Pair<>(exAnteCostOverviewReportRequest, aggregatedReport), aggregatedReportXml);

         }
      }

      if (reportsGenerated == 0) {
         return null;
      }

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> detailedTasks = new ArrayList<>();
      createTasksForPdfCreation(context, detailedMap, detailedTasks);

      List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> aggregatedTask = new ArrayList<>();
      createTasksForPdfCreation(context, aggregatedMap, aggregatedTask);

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

      Report aggregatedMergedReport = requestReportBABean.createInitialReportForNdg(
          exAnteCostOverviewReportRequestList.get(0).getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT);

      Report detailedMergedReport = requestReportBABean.createInitialReportForNdgWithReportId(
          exAnteCostOverviewReportRequestList.get(0).getNdg(), ReportTypeDefinition.EX_ANTE_COST_REPORT,
          aggregatedMergedReport.getReportId() + "_D");

      buildAndSaveMergedReport(aggregatedReports, disclaimerPdf, aggregatedMergedReport);

      buildAndSaveMergedReport(detailedReports, disclaimerPdf, detailedMergedReport);

      return aggregatedMergedReport.getReportId();
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

   private void createTasksForPdfCreation(RequestContext context, Map<Pair<ExAnteCostOverviewReportRequest, Report>, String> detailedMap, List<Pair<Pair<ExAnteCostOverviewReportRequest, Report>, Future<byte[]>>> detailedTasks) {
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
