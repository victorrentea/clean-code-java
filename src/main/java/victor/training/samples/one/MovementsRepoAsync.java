package victor.training.samples.one;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface MovementsRepoAsync {
   Future<List<AccountMovements>> findInvestmentWithdrawalsRealCashflow(RequestContext ctx, Long rodCode, List<Account> finalAccounts, Date startDate, Date endDate, String contractNo, ReportInstrumentScopeType instrumentType);

   Future<List<AccountMovements>> findAccountMovementsRealCashflow(RequestContext ctx, Long rodCode, List<Account> liquidityAccounts, Date startDatePlusOneDay, List<Date> cashDateList, String contractNo, ReportInstrumentScopeType instrumentType);
}
