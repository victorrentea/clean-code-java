package videostore.dirty;

import java.util.List;
import java.util.stream.Collectors;

public class StatementFormatter {

   public String formatStatement(String customerName, List<Rental> rentals) {
      return createHeader(customerName)
             + createBody(rentals)
             + createFooter(rentals);
   }

   private String createBody(List<Rental> rentals) {
      return rentals.stream().map(this::createStatementLine).collect(Collectors.joining());
   }

   private double computeTotalPrice(List<Rental> rentals) {
      double totalPrice = 0.0;
      for (Rental rental : rentals) {
         totalPrice += rental.computePrice();
      }
      return totalPrice;
   }

   private int computeTotalRenterPoints(List<Rental> rentals) {
      int frequentRenterPoints = 0;
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeFrequentRenterPoints();
      }
      return frequentRenterPoints;
   }

   private String createHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String createStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String createFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n"
             + "You earned " + computeTotalRenterPoints(rentals) + " frequent renter points";
   }
}
