package victor.training.samples.one;

import org.joda.time.LocalDate;
import sun.security.krb5.internal.KerberosTime;

import java.util.Date;
import java.util.Map;


public class DefaultDataSet {
   private LocalDate startDate;
   private LocalDate endDate;
   private Map<Date, String> liquidityMovementsDateMap;

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public Long getPerfPeriods() {
      return null;
   }

   public LocalDate getEndDate() {
      return endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public void setLiquidityMovementsDateMap(Map<Date, String> liquidityMovementsDateMap) {
      this.liquidityMovementsDateMap = liquidityMovementsDateMap;
   }

   public Map<Date, String> getLiquidityMovementsDateMap() {
      return liquidityMovementsDateMap;
   }
}
