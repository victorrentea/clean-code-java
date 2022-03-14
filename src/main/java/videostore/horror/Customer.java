package videostore.horror;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
// WHEN is it safe to call a function 2 times
// 1) No SIDE-EFFECTs
// :that is, DO NOT repeat calls if the function INSERTs to DB.
// 2) REFERENTIAL TRANSPARENT (~idempotent): "same params -> same result"
// double defaultPrice = restTemplate.getForDouble("http:://some-service");// NOT safe to re-call
// *) FAST <- also concerned
// that is, a CPU intensive function should be cached.

// 1+2 = PURE Function
// it never goes outside the MEMORY.

public class Customer {
   private final String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public void addRental(Rental rental) {
      rentals.add(rental);
   }

   public String getName() {
      return name;
   }

   public String statement() {
      return formatHeader()
             + formatBody()
             + formatFooter();
   }

   /**
    * - pure functions are cool: repeat them and you're still safe.
    * - immutable object are cool > see above why
    * - broke the for take totalAmount computation out
    * - we identified the Rental class
    * - we added contraints and we had lovely discussions with BIZ >
    * - we made our model null-safe
    * - moved biz logic inside Rental
    * - symmetry: header/footer
    */

   private String formatBody() {
      return rentals.stream().map(this::formatBodyRecord).collect(joining());
   }

   private String formatBodyRecord(Rental rental) {
      return "\t" + rental.movie().title() + "\t" + rental.price() + "\n";
   }

   private String formatHeader() {
      return "Rental Record for " + getName() + "\n";
   }

   private String formatFooter() {
      double totalPrice = rentals.stream().mapToDouble(Rental::price).sum();
      int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
      String result = "Amount owed is " + totalPrice + "\n";
      result += "You earned " + frequentRenterPoints + " frequent renter points";
      return result;
   }

}