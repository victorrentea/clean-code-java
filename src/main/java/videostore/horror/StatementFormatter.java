package videostore.horror;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class StatementFormatter {

   public String createStatement(String customerName, List<Rental> rentals) {
      return createHeader(customerName)
          + createBody(rentals)
          + createFooter(rentals);
   }

   private String createHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String createBody(List<Rental> rentals) {
      return rentals.stream().map(this::createStatementLine).collect(joining());
   }

   private String createStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String createFooter(List<Rental> rentals) {
//      rentals.stream().map(this::createStatementLine).collect(Collectors.toList());
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
          "You earned " + computeTotalPoints(rentals) + " frequent renter points";
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }
}
