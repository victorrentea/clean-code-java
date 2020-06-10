package victor.training.refactoring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class GuardClauses {
   public int getPayAmount(Marine marine) {
      int result;
      if (!marine.isDead()) {
         if (!marine.isRetired()) {
            if (marine.getYearsService() != null) {
               result = marine.getYearsService() * 100;
               if (!marine.getAwards().isEmpty()) {
                  result += 1000;
               }
               if (marine.getAwards().size() >= 3) {
                  result += 2000;
               }
            } else {
               throw new IllegalStateException("Any marine should have the years of service set");
            }
         } else {
            result = retiredAmount();
         }
      } else {
         result = deadAmount();
      }
      return result;
   }

   private int deadAmount() {
      return 1;
   }

   private int retiredAmount() {
      return 2;
   }

}

class Marine {
   private boolean dead;
   private boolean retired;
   private Integer yearsService;
   private List<Award> awards = new ArrayList<>();

   public Marine() {
   }

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

   public Marine setDead(boolean dead) {
      this.dead = dead;
      return this;
   }

   public Marine addAward(Award... awards) {
      this.awards.addAll(Arrays.asList(awards));
      return this;
   }

   public Marine setRetired(boolean retired) {
      this.retired = retired;
      return this;
   }

   public Marine setYearsService(Integer yearsService) {
      this.yearsService = yearsService;
      return this;
   }
}

class Award {

}