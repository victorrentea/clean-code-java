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

  public void addRental(Movie rental, int daysRented) {
    rentals.add(new Rental(rental, daysRented));
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
    double totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
    int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
    return "Amount owed is " + totalPrice + "\n" +
           "You earned " + frequentRenterPoints + " frequent renter points";
  }

  private String statementLine(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";
  }


}