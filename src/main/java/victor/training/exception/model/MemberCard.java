package victor.training.exception.model;

public class MemberCard {
   public static final MemberCard NO_CARD = new MemberCard(0);
   private int fidelityPoints = 1;

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
