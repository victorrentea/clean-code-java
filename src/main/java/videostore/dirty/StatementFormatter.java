package videostore.dirty;

import victor.training.cleancode.pretend.Service;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class StatementFormatter {
   public String formatStatement(List<Rental> rentals, String customerName) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::composeRentalTitleAndPrice).collect(joining());
   }

   private String composeRentalTitleAndPrice(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      String result = "Amount owed is " + determineTotalPrice(rentals) + "\n";
      result += "You earned " + determineTotalPoints(rentals) + " frequent renter points";
      return result;
   }

   private int determineTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::determineFrequentRenterPoints).sum();
   }

   private double determineTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::determinePrice).sum();
   }

}
