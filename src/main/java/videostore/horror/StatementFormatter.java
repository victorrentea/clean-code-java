package videostore.horror;

import java.util.List;
import java.util.stream.Collectors;

public class StatementFormatter {
   public String formatStatement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName) +
             formatBody(rentals) +
             formatFooter(rentals);
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatStatementLine).collect(Collectors.joining());
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
             "You earned " + computeTotalPoints(rentals) + " frequent renter points";
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   // TODO Encapsulate the statement as a Statmeent class that knows how to convert itself to a string
}
