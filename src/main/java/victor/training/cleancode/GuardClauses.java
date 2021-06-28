package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      int result;
      if (!retrieveDeadStatus()) { // network call
         if (marine != null) {
            if (!marine.isRetired()) {
               if (marine.getYearsService() != null) {
                  result = marine.getYearsService() * 100;
                  if (!marine.getAwards().isEmpty()) {
                     result += 1000;
                  }
                  if (marine.getAwards().size() >= 3) {
                     result += 2000;
                  }
                  // much more logic here...
               } else {
                  throw new IllegalArgumentException("Any marine should have the years of service set");
               }
            } else result = retiredAmount();
         } else {
            throw new RuntimeException("Marine is null");
         }
      } else {
         // some logic here
         result = deadAmount();
      }
      return result;
   }

   private boolean retrieveDeadStatus() {
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

class Marine {
   private final boolean dead;
   private final boolean retired;
   private final Integer yearsService;
   private final List<Award> awards = new ArrayList<>();

   Marine(boolean dead, boolean retired, Integer yearsService) {
      this.dead = dead;
      this.retired = retired;
      this.yearsService = yearsService;
   }

   public List<Award> getAwards() {
      return awards;
   }

   public Integer getYearsService() {
      return yearsService;
   }

   public boolean isRetired() {
      return retired;
   }

   public boolean isDead() {
      return dead;
   }
}

class Award {

}