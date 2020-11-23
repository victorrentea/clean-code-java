package videostore.dirty;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {

   public String generateStatement(String customerName, List<Rental> rentals) {
      return createHeader(customerName) +
             createBody(rentals) +
             createFooter(rentals);
   }

   private String createBody(List<Rental> rentals) {
      return rentals.stream().map(this::createBodyLine).collect(joining());
   }

   private String createBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
   }

   private String createHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String createFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
             "You earned " + computeTotalPoints(rentals) + " frequent renter points";
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
   }

}
