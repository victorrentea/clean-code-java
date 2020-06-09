package victor.training.refactoring;

public class ReplaceTempWithQuery {

   private int quantity;
   private double itemPrice;

   public ReplaceTempWithQuery(int quantity, double itemPrice) {
      this.quantity = quantity;
      this.itemPrice = itemPrice;
   }


   public double computePrice() {
      if (quantity > 10)
         return basePrice() * 0.95;
      else
         return basePrice() * normalPriceFactor();
   }

   //    @Contract(pure=true)
   private double basePrice() {
//        sql.insert(BOU);
      return quantity * itemPrice;
   }

   private double normalPriceFactor() {
      if (basePrice() > 1000) {
         return 0.95;
      } else {
         return 0.98;
      }
   }

   public int computeFidelityPoints() {
      return (int) (basePrice() / 2);
   }
}
