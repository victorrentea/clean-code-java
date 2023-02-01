package victor.training.cleancode;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class GuardClauses {


   // pai tu m-ai pus sa fixez Sonaru (sa cresc nota)
   // si in clasa asta Sonar zicea ca 'repetead literal'
   // ca compuneam un export file CSV cu multe spatii
   // DRAMA: a luat si spatiul dintre First LAST si spatiul dintre CELULE
   private static final String SPACE = " ";

   public static final int DEAD_PAY_AMOUNT = 1;

   public int getPayAmount(@NotNull Marine marine) {
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
      return computeRegularPayAmount(marine);
   }

   // ideal chiar in alta clasa: asta de jos in Domain Service
   private static int computeRegularPayAmount(Marine marine) {
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