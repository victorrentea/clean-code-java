package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.ArrayList;
import java.util.List;

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
      double totalPrice = 0;
      int frequentRenterPoints = 0;
      String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			// add frequent renter points
         frequentRenterPoints++;
         // add bonus for a two day new release rental
         if (movie.priceCode() == PriceCode.NEW_RELEASE && daysRented >= 2) {
            frequentRenterPoints++;
         }

         // show figures line for this rental
         result += "\t" + movie.title() + "\t" + rental.calculatePrice() + "\n";
         totalPrice += rental.calculatePrice();

         // WHEN is it safe to call a function 2 times
         // 1) No SIDE-EFFECTs
               // :that is, DO NOT repeat calls if the function INSERTs to DB.
         // 2) REFERENTIAL TRANSPARENT (~idempotent): "same params -> same result"
               // double defaultPrice = restTemplate.getForDouble("http:://some-service");// NOT safe to re-call
         // *) FAST <- also concerned
               // that is, a CPU intensive function should be cached.

         // 1+2 = PURE Function
      }
      // add footer lines
      result += "Amount owed is " + totalPrice + "\n";
      result += "You earned " + frequentRenterPoints + " frequent renter points";
      return result;
   }

}