package videostore.dirty;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
   private final String name;
   private final List<Rental> rentals = new ArrayList<Rental>();

   public Customer(String name) {
      this.name = name;
   }

   ;

   public void addRental(Rental rental) {
      rentals.add(rental);
   }

   public String getName() {
      return name;
   }

   public String statement() {
      return createHeader()
             + createBody()
             + createFooter();
   }

   private String createBody() {
      return rentals.stream().map(this::createStatementLine).collect(Collectors.joining());
   }

   private double computeTotalPrice() {
      double totalPrice = 0.0;
      for (Rental rental : rentals) {
         totalPrice += rental.computePrice();
      }
      return totalPrice;
   }

   private int computeTotalRenterPoints() {
      int frequentRenterPoints = 0;
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeFrequentRenterPoints();
      }
      return frequentRenterPoints;
   }

   private String createHeader() {
	return "Rental Record for " + name + "\n";
}

   private String createStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String createFooter() {
      return "Amount owed is " + computeTotalPrice() + "\n"
             + "You earned " + computeTotalRenterPoints() + " frequent renter points";
   }
}