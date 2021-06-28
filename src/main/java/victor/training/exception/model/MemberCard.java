package victor.training.exception.model;

public class MemberCard {
   private int fidelityPoints = 1;

   public int getFidelityPoints() {
      return fidelityPoints;
   }

   public MemberCard setFidelityPoints(int fidelityPoints) {
      this.fidelityPoints = fidelityPoints;
      return this;
   }
}
