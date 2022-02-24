package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {

   public int getPayAmount(Marine marine) {
      // GUNOI care nu trebuie sa fie amestecat cu logica efectiva.
      if (marine == null) { // guard condition
         throw new RuntimeException("Marine is null");
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }

      return bizPayAmount(marine);
   }

   private int bizPayAmount(Marine marine) {
      if (isDead(marine)) {
         return deadAmount();
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      // Feature Envy
      int result = marine.getPay();
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

   public int getPay() {
      int result = yearsService * 100;
      if (!awards.isEmpty()) {
         result += 1000;
      }
      if (awards.size() >= 3) {
         result += 2000;
      }
      return result;
   }
}

class Award {

}