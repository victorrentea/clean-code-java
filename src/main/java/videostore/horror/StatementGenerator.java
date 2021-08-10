package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {
   public String generateStatement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatStatementLine).collect(joining());
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      String result = "Amount owed is " + computeTotalPrice(rentals) + "\n";
      result += "You earned " + computeTotalPoints(rentals) + " frequent renter points";
      return result;
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

}
