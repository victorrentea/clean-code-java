package videostore.horror;

import lombok.Value;
import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class StatementGenerator {


   public String generateStatement(String customerName, List<Rental> rentals) {

      return formatHeader(customerName)
             + formatBody(rentals)
             + formatFooter(rentals);
   }

   private String formatHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String formatBody(List<Rental> rentals) {
      return rentals.stream().map(this::formatBodyLine).collect(joining());
   }

   private String formatBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatFooter(List<Rental> rentals) {
      return "Amount owed is " + computeTotalPrice(rentals) + "\n"
             + "You earned " + computeTotalPoints(rentals) + " frequent renter points";
   }

   private double computeTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::computePrice).sum();
   }

   private int computeTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::computePoints).sum();
   }
   // "separation by layers of abstraction" -- codul dintr-o metoda trebuie sa fie aprox la acelasi nivel de detaliu

}
