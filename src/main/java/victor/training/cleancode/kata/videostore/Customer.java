package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return generateHeader() + generateBody() + generateFooter();
  }

  private String generateBody() {
    return rentals.stream().map(this::statementLine).collect(Collectors.joining());
  }

  private String generateHeader() {
    return "Rental Record for " + name + "\n";
  }

  private String generateFooter() {
    int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
    return "Amount owed is " + calculateTotalPrice() + "\n" +
           "You earned " + frequentRenterPoints + " frequent renter points";
  }

  private double calculateTotalPrice() {
    double totalPrice = 0.0;
    for (Rental rental : rentals) {
      double calculatePrice = rental.calculatePrice();
      totalPrice += calculatePrice;
    }
    return totalPrice;
  }

  private String statementLine(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";
  }


}