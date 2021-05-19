package videostore.horror;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {



   public String generateStatement(String customerName, List<Rental> rentals) {

//      AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);

//      for (Rental rental : rentals) {
//      rentals.forEach(rental -> {
//         totalPrice.updateAndGet(v -> new Double((double) (v + rental.computePrice())));
//      });
      for (Rental r : rentals) {
         if (r.getMovie().isNewRelease()) {
            System.out.println(r);
         }
      }
//      Tuple5<String,String,String,Double, Integer>

      return generateHeader(customerName) +
             generateBody(rentals) +
             generateFooter(rentals);
   }

   private String generateFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n" +
             "You earned " + computeTotalPoints(rentals) + " frequent renter points";
   }

   private String generateBody(List<Rental> rentals) {
      return rentals.stream().map(this::generateStatementLine).collect(joining());
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
   }

   private String generateHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String generateStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }
}
