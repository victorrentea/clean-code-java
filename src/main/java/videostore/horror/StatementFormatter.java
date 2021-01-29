package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementFormatter {
   public String statement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatBodyLine).collect(joining());
   }

   private String formatFooter(List<Rental> rentals) {
      double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();
      int totalPoints = rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + totalPoints + " frequent renter points";
   }

   private String formatBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

}
