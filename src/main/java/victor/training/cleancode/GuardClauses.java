package victor.training.cleancode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      if (marine == null) {
         throw new RuntimeException("Marine is null");
      }
      if (isDead(marine)) {
         return deadAmount();
      } // network call
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }
      return marine.computePay();
      // HEAVY logic here...
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

   // bits of small (5-10) reusable (not specific to a use case) DOMAIN LOGIC
   public int computePay() {
      int result = yearsService * 100;
      if (!awards.isEmpty()) {
         result += 1000;
      }
      if (awards.size() >= 3) {
         result += 2000;
      }
      return result;
   }

   public String asCsv() { // BAD its presentation logic - violating MVC
      return dead + "," + retired + "," + yearsService;
   }
}
@Service
class MarinePaymentCalculator {

}

class Award {

}


// py
//   m(g)()
//fun m(g) {
//
//}