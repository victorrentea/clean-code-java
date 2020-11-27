package videostore.dirty;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CustomerTest {

   @Test
   public void characterizationTest() {
      Customer customer = new Customer("John Doe");
      customer.addRental(new Rental(new Movie("Star Wars", Movie.Category.NEW_RELEASE), 6));
      customer.addRental(new Rental(new Movie("Sofia", Movie.Category.CHILDRENS), 7));
      customer.addRental(new Rental(new Movie("Inception", Movie.Category.REGULAR), 5));

      String expected = "Rental Record for John Doe\n"
                        + "	Star Wars	18.0\n"
                        + "	Sofia	7.5\n"
                        + "	Inception	6.5\n"
                        + "Amount owed is 32.0\n"
                        + "You earned 4 frequent renter points";

      int t = 0;
//      List<String> allTags = customer.getRentals().stream().flatMap(r -> r.getTags().stream()).collect(Collectors.toList());
      List<String> allTags = getAllTags(customer);

      for (String tag : allTags) {
         System.out.println("tag: " + tag);
         t += tag.length();
      }
      System.out.println("t per rental" + t);


      Assert.assertEquals(expected,
          new StatementFormatter()
              .formatStatement(customer.getRentals(), customer.getName()));
   }

   private List<String> getAllTags(Customer customer) {
      List<String> allTags = new ArrayList<>();
      for (Rental rental : customer.getRentals()) {
         allTags.addAll(rental.getTags());
      }
      return allTags;
   }
}
