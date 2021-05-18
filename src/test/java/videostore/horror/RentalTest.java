package videostore.horror;

import org.junit.Test;
import videostore.horror.Movie.Type;

import static org.junit.Assert.*;

public class RentalTest {

   @Test
   public void computePrice() {
      for (Type type : Type.values()) {
         new Rental(new Movie("", type),1).computePrice();
      }
   }
}