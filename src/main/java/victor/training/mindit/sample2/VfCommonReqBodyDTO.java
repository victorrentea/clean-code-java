package victor.training.mindit.sample2;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class VfCommonReqBodyDTO {
   public LocalDate getStartDate() {
      return null;
   }

   public LocalDate getEndDate() {
      return null;
   }

   public List<Long> getBrandIds() {
      return null;
   }

   public List<Long> getCategoryIds() {
      return null;
   }

   public DateInterval getDateInterval() {
      return new DateInterval(getStartDate(), getEndDate());
   }
}
