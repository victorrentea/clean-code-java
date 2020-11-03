package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

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

   public String createStatement() {
      return createHeader() +
             createBody() +
             createFooter();
   }

   private String createBody() {
      return rentals.stream().map(this::createBodyLine).collect(joining());
   }

   private String createFooter() {
      return "Amount owed is " + computeTotalPrice() + "\n"
             + "You earned " + computeTotalPoints() + " frequent renter points";
   }

   private double computeTotalPrice() {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints() {
      return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   private String createBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String createHeader() {
      return "Rental Record for " + name + "\n";
   }

}