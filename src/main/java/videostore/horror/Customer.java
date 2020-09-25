package videostore.horror;

import java.util.*;

class Customer {
   private String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public List<Rental> getRentals() {
      return rentals;
   }

   public void addRental(Movie movie, int daysRented) {
      rentals.add(new Rental(movie, daysRented));
   }

}

class StatementGenerator {

   public String generateStatement(String customerName, List<Rental> rentals) {
      double totalAmount = 0;
      int frequentRenterPoints = 0;
      String statement = formatHeader(customerName);
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeFrequentRenterPoints();
         statement += formatStatementLine(rental);
         totalAmount +=  rental.computePrice();
      }
      statement += formatFooter(totalAmount, frequentRenterPoints);
      return statement;
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(double totalAmount, int frequentRenterPoints) {
      return "Amount owed is " + totalAmount + "\n"
             + "You earned " + frequentRenterPoints + " frequent renter points";
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

}