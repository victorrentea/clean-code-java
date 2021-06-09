package victor.training.samples.one;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface CurrentOrderRepoAsync {
//   @Async
   Future<List<AccountFlightOrder>> findInflightOrders(RequestContext ctx, Date startDate, List<Account> accountsWithInflightOrders, Long rodCode, String contractNo, ReportInstrumentScopeType instrumentType);

}
