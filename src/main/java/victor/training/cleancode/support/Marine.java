package victor.training.cleancode.support;

import java.util.ArrayList;
import java.util.List;

public class Marine {
   private final boolean dead;
   private final boolean retired;
   private final Integer yearsService;
   private final List<Award> awards = new ArrayList<>();

   public Marine(boolean dead, boolean retired, Integer yearsService) {
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