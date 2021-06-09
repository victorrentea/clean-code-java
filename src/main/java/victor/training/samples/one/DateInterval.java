package victor.training.samples.one;

import java.util.Date;

public class DateInterval {
   private final Date startDate;
   private final Date endDate;

   public DateInterval(Date startDate, Date endDate) {
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public Date getStartDate() {
      return startDate;
   }

   public Date getEndDate() {
      return endDate;
   }
}
