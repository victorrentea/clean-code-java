package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      if (retrieveDeadStatus()) {
         // some logic here
         return deadAmount();
      } // network call
      if (marine == null) { // smells like a bug fix
         throw new RuntimeException("Marine is null");
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) { // smells like data corruption fixes.
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }

      return computeRegularAmount(marine);
   }

   private int computeRegularAmount(Marine marine) {
      int result = marine.getYearsService() * 100;
      if (!marine.getAwards().isEmpty()) {
         result += 1000;
      }
      if (marine.getAwards().size() >= 3) {
         result += 2000;
      }
      // much more logic here...
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