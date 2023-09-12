package victor.training.cleancode.exception.model;

public class MemberCard {
   private int fidelityPoints = 1;
   private String email;

   public MemberCard() {}
   public MemberCard(int fidelityPoints) {
      this.fidelityPoints = fidelityPoints;
   }

   public int getFidelityPoints() {
      return fidelityPoints;
   }

   public MemberCard setFidelityPoints(int fidelityPoints) {
      this.fidelityPoints = fidelityPoints;
      return this;
   }
}
