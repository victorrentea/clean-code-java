package victor.training.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      if (marine == null) {
         throw new RuntimeException("Marine is null");
      }
      if (retrieveDeadStatus()) {
         // some logic here
        return deadAmount();
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }

      return computeRegularPay(marine);
   }

   private int computeRegularPay(Marine marine) {
      // aici e cazul cel mai intersant: ci cititorul nu mai e stalcit de if-urile de deasupra. E relaxat.
      int result = marine.getYearsService() * 100;
      boolean decorated = !marine.getAwards().isEmpty();
      if (decorated) {
         result += 1000;
      }
      boolean hero = marine.getAwards().size() >= 3;
      if (hero) {
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