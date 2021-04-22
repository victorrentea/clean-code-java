package videostore.dirty;

public class PriceCalculator {

   public double computeChildrenPrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   public double computeNewReleasePrice(int daysRented) {
      return daysRented * 3;
   }

   public double computeRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
}
