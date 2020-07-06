package victor.training.refactoring;

import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

class MarineValidator {}
class GuardService {
   public int getPayAmount(Marine marine) {
      if (marine.isDead()) {
         return deadAmount();
      }
      if (marine.isRetired()) {
         return retiredAmount();
      }
      if (marine.getYearsService() == null) {
         throw new IllegalStateException("Any marine should have the years of service set");
      }
      return computeNormalPayAmount(marine);
   }

   private int computeNormalPayAmount(Marine marine) {
      int result = marine.getYearsService() * 100;
      if (marine.wasAwarded()) {
         result += 1000;
      }
      boolean manyAwards = marine.getAwards().size() >= 3;
      if (manyAwards) {
         result += 2000;
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


public class GuardClauses {
   private GuardService guards = new GuardService();

   @Test
   public void dead() {
      Marine marine = new Marine().setDead(true);
      assertEquals(1, guards.getPayAmount(marine));
   }

   @Test
   public void retired() {
      Marine marine = new Marine().setRetired(true);
      assertEquals(2, guards.getPayAmount(marine));
   }

   @Test(expected = IllegalStateException.class)
   public void nullYears() {
      Marine marine = new Marine().setYearsService(null);
      guards.getPayAmount(marine);
   }

   @Test
   public void noAwards() {
      Marine marine = new Marine().setYearsService(5);
      assertEquals(500, guards.getPayAmount(marine));
   }

   @Test
   public void oneAward() {
      Marine marine = new Marine()
          .setYearsService(5)
          .addAward(new Award());
      assertEquals(1500, guards.getPayAmount(marine));
   }

   @Test
   public void manyAwards() {
      Marine marine = new Marine()
          .setYearsService(5)
          .addAward(new Award(), new Award(), new Award());
      assertEquals(3500, guards.getPayAmount(marine));
   }
}



class Marine {
   private boolean dead;
   private boolean retired;
//   private MarineHistory history; // for destructuring into multiple object. If using JPA, mind @Embeddable
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

   public boolean wasAwarded() {
      return !awards.isEmpty();
   }
}

class Award {

}