package victor.training.samples.one;

import victor.training.samples.one.other.ReportInstrumentScopeType;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public class AssetClassRepoAsync {
   public Future<ClassifiedPortfolioPerformance> calculatePortfolioAndAccountPerformance(RequestContext ctx, Date startDate, Date endDate, List<Account> performanceAccounts, Long rodCode, String contractNo, ReportInstrumentScopeType instrumentTypePerf) {
      return null;
   }
}
