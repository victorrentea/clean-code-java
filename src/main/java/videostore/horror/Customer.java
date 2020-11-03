package videostore.horror;

import java.util.*;

class Customer {

   private final String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public void addRental(Movie movie, int daysRented) {
      rentals.add(new Rental(movie, daysRented));
   }

   public String getName() {
      return name;
   }

   public String statement() {
      double totalPrice = 0;
      int frequentRenterPoints = 0;
      String result = createHeader();

      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeRenterPoints();
         result += createBodyLine(rental, rental.determinePrice());
         totalPrice += rental.determinePrice();
         // este ok sa repeti un apel de functie daca e pura si rapida.
      }
      result += createFooter(totalPrice, frequentRenterPoints);
      return result;
   }

   private String createBodyLine(Rental rental, double price) {
      return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
   }

   private String createHeader() {
      return "Rental Record for " + name + "\n";
   }

   private String createFooter(double totalPrice, int frequentRenterPoints) {
      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + frequentRenterPoints + " frequent renter points";
   }

}