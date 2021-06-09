package victor.training.samples.one;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface PortfolioIncomeRepoAsync {
   Future<List<AccountCouponDividend>> findCouponDividends(RequestContext ctx, List<Account> securityDepositAccounts, Date startDate, Date startDate1, Long rodCode, String contractNo, ReportInstrumentScopeType instrumentType);
}
