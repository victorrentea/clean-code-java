package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String statement() {
    return header() + body() + footer();
  }

  private String header() {
    return "Rental Record for " + name + "\n";
  }

  private String body() {
    return rentals.stream().map(this::rentalLine).collect(joining());
  }

  private String rentalLine(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.price() + "\n";
  }

  private String footer() {
    return "Amount owed is " + totalPrice() + "\n"
           + "You earned " + totalPoints() + " frequent renter points";
  }

  private double totalPrice() {
    return rentals.stream().mapToDouble(Rental::price).sum();
  }

  private int totalPoints() {
    return rentals.stream().mapToInt(Rental::frequentRenterPoints).sum();
  }
}