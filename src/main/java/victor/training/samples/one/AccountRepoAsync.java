package victor.training.samples.one;

import javax.persistence.EmbeddedId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public class AccountRepoAsync {
   public Future<List<Account>> addBalancesAndProducts(RequestContext ctx, Long rodCode, List<Account> finalAccounts, Date endDate, String contractNo, ReportInstrumentScopeType instrumentType) {
      return null;
   }

   public Future<List<AccountBalance>> findVirtualCashflow(RequestContext ctx, Long rodCode, List<Account> liquidityAccounts, Date startDate, Date endDate, String contractNo, ReportInstrumentScopeType instrumentType) {
      OrderId o = new OrderId(1);
      CustomerId c = new CustomerId(2);
      method(o, c,3,4,5,6);
      return null;
   }

   public void method(OrderId a, CustomerId b, int c, int d, long e, long f) {
   }
}
//@EmbeddedId
class OrderId {
   private final long id;

   OrderId(long id) {
      this.id = id;
   }

   public long getId() {
      return id;
   }
}
class CustomerId {
   private final long id;

   CustomerId(long id) {
      this.id = id;
   }

   public long getId() {
      return id;
   }
}
