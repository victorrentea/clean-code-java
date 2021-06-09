package videostore.horror;

import java.util.List;
import java.util.stream.Collectors;

public class StatementFormatter {

   public String formatStatement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
   }

   private String formatBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() +
             "\t" + rental.calculatePrice() + "\n";
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateBonus).sum();
      double totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();

      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + frequentRenterPoints + " frequent renter points";
   }


}
