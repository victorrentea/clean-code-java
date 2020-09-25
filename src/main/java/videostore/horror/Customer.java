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
      return formatHeader(customerName) +
             formatBody(rentals) +
             formatFooter(rentals);
   }

   private String formatBody(List<Rental> rentals) {
      String statement = "";
      for (Rental rental : rentals) {
         statement += formatStatementLine(rental);
      }
      return statement;
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n"
             + "You earned " + computeTotalRenterPoints(rentals) + " frequent renter points";
   }

   private int computeTotalRenterPoints(List<Rental> rentals) {
      int frequentRenterPoints = 0;
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeFrequentRenterPoints();
      }
      return frequentRenterPoints;
   }

   private double computeTotalPrice(List<Rental> rentals) {
      double totalPrice = 0;
      for (Rental rental : rentals) {
         totalPrice +=  rental.computePrice();
      }
      return totalPrice;
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