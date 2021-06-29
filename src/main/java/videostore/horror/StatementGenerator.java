package videostore.horror;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;
class SomeOtherClassInYourApp {

   @Autowired
   private StatementGenerator statementGenerator;

   public void method() {
      // test this
      // test this
      // test this
      statementGenerator.generateStatement("", Collections.emptyList());
      // test this
      // test this
      // test this
      // test this
      // test this
   }
}

@Service
public class StatementGenerator {

   // {totalPrice, totalPoints and a List<{title:string, price:double}>}
   public String generateStatement(String customerName, List<Rental> rentals) { // presentation
      return generateHeader(customerName)
             + generateBody(rentals)
             + generateFooter(rentals);
   }

   private String generateBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatRental).collect(joining());
   }

   private String formatRental(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String generateHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String generateFooter(List<Rental> rentals) {
      int totalPoints = rentals.stream().mapToInt(Rental::computeFrequentPoints).sum();
      double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();

      String result = "Amount owed is " + totalPrice + "\n";
      result += "You earned " + totalPoints + " frequent renter points";
      return result;
   }


}
