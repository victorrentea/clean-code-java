package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  @Getter
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String pName) {
    this.name = pName;
  }

  private static String bodyLine(Rental rental) {
    return "\t" + rental.movie().getTitle() + "\t" + rental.price() + "\n";
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String statement() {
    return header() + body() + footer();
  }

  private String footer() {
    int frequentRenterPoints = rentals.stream()
        .mapToInt(Rental::frequentRenterPoints)
        .sum();

    double totalAmount = rentals.stream()
        .mapToDouble(Rental::price)
        .sum();

    String result = "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private String body() {
    return rentals.stream()
        .map(Customer::bodyLine)
        .collect(Collectors.joining());
  }

  private String header() {
    return "Rental Record for " + name + "\n";
  }

}