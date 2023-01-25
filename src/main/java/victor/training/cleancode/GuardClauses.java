package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class GuardClauses {

   public static final int DEAD_PAY_AMOUNT = 1;

   public int getPayAmount(Marine marine) {
      if (marine == null) {
         throw new RuntimeException("Marine is null");
      }
      if (isDead(marine)) {
         return DEAD_PAY_AMOUNT;
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }
      if (marine.getAwards() == null) {
         throw new RuntimeException("WHAT?!! colectie nula la mine in cod !?!?!");
      }
      return computePayAmount(marine);
   }

   private static int computePayAmount(Marine marine) {
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
      return false;
   }

   private int retiredAmount() {
      return 2;
   }

}

class Marine {
   private final boolean dead;
   private final boolean retired;
   private final Integer yearsService;
   private final List<Award> awards = new ArrayList<>(); // daca vreodata prind colectii nulle FAC SCANDAL!
   //vin la tine. Niciodata nu ai voie sa primesti ca List<> un null in codul central. NICIODATA> doar colectii goale.

   // motiv de scandal:
   // JSF,Jackson, JAXB -> asjustezi frameworkuri
   //

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