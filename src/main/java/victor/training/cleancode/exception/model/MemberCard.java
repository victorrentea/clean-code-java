package victor.training.cleancode.exception.model;

public class MemberCard {
   private String barcode;
   private int fidelityPoints = 1;
   private String email;

   public MemberCard(String barcode) {
      this.barcode = barcode;
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

   public void setFidelityPoints(int fidelityPoints) {
      this.fidelityPoints = fidelityPoints;
   }
}
