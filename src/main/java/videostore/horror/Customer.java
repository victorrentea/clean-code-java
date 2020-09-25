package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatStatementLine).collect(joining());
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n"
             + "You earned " + computeTotalRenterPoints(rentals) + " frequent renter points";
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalRenterPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
   }

}