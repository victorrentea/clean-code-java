package videostore.horror;

import lombok.Value;

@Value
public class Rental {
   Movie movie;
   int daysRented;

   public double calculatePrice() {



      double price = defaultPrice;
      switch (getMovie().priceCode()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            break;
      }
      return price;
   }
}
