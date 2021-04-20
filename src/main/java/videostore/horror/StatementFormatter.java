package videostore.horror;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.joining;
// nighmare time
//class CentralModule {
//   List<Class<?>> allBeans = Arrays.asList(
//       SomeDep.class,
//       StatementFormatter.class,
//       );
//}

//class SomeDep {
//
//}
class ProdCodeUsingFormatter {
   @Inject
   StatementFormatter formatter;
   public void method() {
      // logic
      // logic TO TEST
//      new StatementFormatter(rentals) // instantiation cannot be easily MOCKED.
      // You can't test the logic surrounding this line giving it a MOCKED Formatter
      // logic TO TEST
   }
}
public class StatementFormatter {
//   private final List<Rental> rentals;
//
//   public StatementFormatter(List<Rental> rentals) {
//      this.rentals = rentals;
//   }

//   @Inject
//   private final SomeDep someDep;
//
//   public StatementFormatter(SomeDep someDep) {
//      this.someDep = someDep;
//   }


   // IF YOU EVER WANT TO MOCK THIS FUNCTION, LEAVE IT INSTANCE
   public String formatStatement(String customerName, List<Rental> rentals) {
      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }



   private int computeTotalFrequentRenterPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::getPrice).sum();
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
             "You earned " + computeTotalFrequentRenterPoints(rentals) + " frequent renter points";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatStatementLine).collect(joining());
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }
}
