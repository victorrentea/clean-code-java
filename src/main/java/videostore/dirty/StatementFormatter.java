package videostore.dirty;

import java.util.List;

public class StatementFormatter {
   public String generateStatement(String customerName, List<Rental> rentals) {
      double totalPrice = 0;
      int frequentRenterPoints = 0; // TODO =.size()
      String result = formatHeader(customerName);
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.determineFrequentRenterPoints();
         result += formatRentalLine(rental);
         totalPrice += rental.determinePrice();
      }
      result += formatFooter(totalPrice, frequentRenterPoints);
      return result;
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(double totalPrice, int frequentRenterPoints) {
      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + frequentRenterPoints + " frequent renter points";
   }

   private String formatRentalLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
   }

}
