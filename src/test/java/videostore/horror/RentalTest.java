package videostore.horror;

import org.junit.jupiter.api.Test;
import videostore.horror.Movie.PriceCode;

class RentalTest {

   @Test
   void price() {

      for (PriceCode priceCode : PriceCode.values()) {
         Rental rental = new Rental(new Movie("a", priceCode), 1);
         rental.price();
      }

   }
}