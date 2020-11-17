package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementGenerator {


   public String generateStatement(List<Rental> rentalList, String customerName) {
      return generateHeader(customerName) +
             generateBody(rentalList) +
             generateFooter(rentalList);
   }

   private String generateBody(List<Rental> rentalList) {
      return rentalList.stream().map(this::generateBodyLine).collect(joining());
   }

   private String generateBodyLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }


   private String generateHeader(String customerName) {
      return "Rental Record for " + customerName + "\n";
   }

   private String generateFooter(List<Rental> rentalList) {
      return "Amount owed is " + computeTotalPrice(rentalList) + "\n"
             + "You earned " + computeTotalPoints(rentalList) + " frequent renter points";
   }

   private int computeTotalPoints(List<Rental> rentalList) {
      return rentalList.stream().mapToInt(Rental::computeEarnedFrequentPoints).sum();
   }

   private double computeTotalPrice(List<Rental> rentalList) {
      return rentalList.stream().mapToDouble(Rental::computePrice).sum();
   }

}
