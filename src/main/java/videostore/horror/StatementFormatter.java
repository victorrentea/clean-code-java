package videostore.horror;

import lombok.Value;
import victor.training.cleancode.pretend.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
//class Facade1 {
//   public void method() {
//      StatementGenerator.generateStatement()
//   }
//}
@Value
class Statement {
   String customerName;
   List<Rental> rentals;

   public Statement(Customer customer) {
      customerName = customer.getName();
      rentals = customer.getRentals();
   }
   public double getTotalPrice() {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   public int getTotalPoints() {
      return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
   }
}
public class StatementFormatter {

   public String formatStatement(Statement statement) {
      return formatHeader(statement.getCustomerName())
             + formatBody(statement.getRentals())
             + formatFooter(statement);
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatStatementLine).collect(joining());
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatFooter(Statement statement) {
      String result = "Amount owed is " + statement.getTotalPrice() + "\n";
      result += "You earned " + statement.getTotalPoints() + " frequent renter points";
      return result;
   }



}
