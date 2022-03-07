package victor.training.cleancode;

import lombok.NonNull;

public class GuardClausesDomainService {
   public int computePayAmount(@NonNull Marine marine) {
      if (isDead(marine)) {
         return deadAmount();
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      int result = marine.getYearsService() * 100;
      if (!marine.getAwards().isEmpty()) {
         result += 1000;
      }
      if (marine.getAwards().size() >= 3) {
         result += 2000;
      }
      // HEAVY logic here...
      return result;
   }


   private boolean isDead(Marine marine) {
      // after 500 millis
      return false;
   }

   private int deadAmount() {
      return 1;
   }

   private int retiredAmount() {
      return 2;
   }
}
