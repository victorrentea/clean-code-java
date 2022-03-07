package victor.training.cleancode;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GuardClauses {
   private final GuardClausesDomainService domainService;
   public int getPayAmount(Marine marine) {
      if (marine == null) {
         throw new RuntimeException("Marine is null");
      }
      if (marine.getYearsService() == null) {
         throw new IllegalArgumentException("Any marine should have the years of service set");
      }

      return domainService.computePayAmount(marine);
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