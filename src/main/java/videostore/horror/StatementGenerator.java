package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

//class CustomerService {
//   private final StatementGenerator statementGenerator;
//
//   CustomerService(StatementGenerator statementGenerator) {
//      this.statementGenerator = statementGenerator;
//   }
//}

public class StatementGenerator {

   public String generateStatement(String customerName, List<Rental> rentals) {
      double totalPrice = computeTotalPrice(rentals);
      int totalPoints = computeTotalPoints(rentals);

      return createHeader(customerName) +
             createBody(rentals) +
             createFooter(totalPrice, totalPoints);
   }

   private String createBody(List<Rental> rentals) {
      return rentals.stream().map(this::createBodyLine).collect(joining());
   }

   private String createFooter(double totalPrice, int totalPoints) {
      return "Amount owed is " + totalPrice + "\n"
             + "You earned " + totalPoints + " frequent renter points";
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   private String createBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String createHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }
}
