package videostore.horror;

import java.util.List;
import java.util.stream.Collectors;


//class StuffController {
//   @Autowired
//   private  StatementWriter writer;
//   public void m() {
//      // logica
//       writer.createStatement("")
//      // logica
//      // logica
//      // logica
//      // logica
//   }
//}

public class StatementWriter {

   public String createStatement(String customerName, List<Rental> rentals) {
      return createHeader(customerName) +
          createBody(rentals) +
          createFooter(rentals);
   }

   private String createBody(List<Rental> rentals) {
      return rentals.stream().map(this::createLine).collect(Collectors.joining());
   }

   private String createFooter(List<Rental> rentals) {
      return "Amount owed is " + determineTotalPrice(rentals) + "\n" +
          "You earned " + determineTotalPoints(rentals) + " frequent renter points";
   }

   private int determineTotalPoints(List<Rental> rentals) {
      return rentals.stream().mapToInt(Rental::determineRenterPoints).sum();
   }

   private double determineTotalPrice(List<Rental> rentals) {
      return rentals.stream().mapToDouble(Rental::determinePrice).sum();
   }

   private String createHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String createLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
   }

}
