package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {

   public String generateStatement(String customerName, List<Rental> rentals) {
      return generateHeader(customerName)
             + generateBody(rentals)
             + generateFooter(rentals);
   }


   private String generateHeader(String name) {
      return "Rental Record for " + name + "\n";
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeBonus).sum();
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private String generateFooter(List<Rental> rentals) {
      int frequentRenterPoints = computeTotalPoints(rentals);
      double totalPrice = computeTotalPrice(rentals);
      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + frequentRenterPoints + " frequent renter points";
   }

   private String generateStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String generateBody(List<Rental> rentals) {
      return rentals.stream().map(this::generateStatementLine).collect(joining());
   }
}
