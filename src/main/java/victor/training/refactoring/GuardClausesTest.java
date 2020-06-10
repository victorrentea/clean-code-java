package victor.training.refactoring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuardClausesTest {
   private GuardClauses guards = new GuardClauses();

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
