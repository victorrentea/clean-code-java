package victor.training.cleancode.kata.videostore;

import java.util.List;
import java.util.stream.Collectors;

public record Statement(
    String customerName,
    double totalAmount,
    int frequentRenterPoints,
    List<Rental> rentals) {

  public String format() {
    String result = "Rental Record for " + customerName + "\n";
    result += rentals.stream().map(this::formatRental).collect(Collectors.joining());
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private String formatRental(Rental rentalAmount) {
    return "\t" + rentalAmount.movie().title() + "\t" + rentalAmount.computePrice() + "\n";
  }
}
