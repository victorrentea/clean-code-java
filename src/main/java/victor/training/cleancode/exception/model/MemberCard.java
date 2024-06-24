package victor.training.cleancode.exception.model;

public class MemberCard {
   private final String barcode;
   private final int fidelityPoints;

   public MemberCard(String barcode) {
      this(barcode, 1);
   }
   public MemberCard(String barcode, int fidelityPoints) {
      this.barcode = barcode;
      this.fidelityPoints = fidelityPoints;
   }

   public String getBarcode() {
      return barcode;
   }

   public int getFidelityPoints() {
      return fidelityPoints;
   }

}
