package videostore.dirty;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementFormatter {
   public String generateStatement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatRentalLine).collect(joining());
   }

   private int determineTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::determineFrequentRenterPoints).sum();
   }

   private double determineTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::determinePrice).sum();
   }

//   class StatementData {
//      double totalPrice = 0;
//      int frequentRenterPoints = 0; // TODO =.size()
//      String result;

//   }
   private String formatRentalLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + determineTotalPrice(rentals) + "\n"
             + "You earned " + determineTotalPoints(rentals) + " frequent renter points";
   }

}
