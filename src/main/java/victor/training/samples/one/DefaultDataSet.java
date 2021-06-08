package victor.training.samples.one;

import org.joda.time.LocalDate;
import sun.security.krb5.internal.KerberosTime;


public class DefaultDataSet {
   private LocalDate startDate;
   private LocalDate endDate;

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
}
