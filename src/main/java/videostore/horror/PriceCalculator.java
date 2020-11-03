package videostore.horror;

public class PriceCalculator {
   public static double computeChildrenPrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   public static double computeRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2) {
         price += (daysRented - 2) * 1.5;
      }
      return price;
   }

   public static double computeNewReleasePrice(int daysRented) {
      return daysRented * 3;
   }
}
