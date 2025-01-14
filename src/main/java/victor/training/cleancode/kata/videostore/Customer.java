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

  public void addRental(Movie movie, int daysOfRental) {
    rentals.add(new Rental(movie, daysOfRental));
  }

  public String getName() {
    return name;
  }

  public String getStatement() {
    return getHeader() + getBody() + getFooter();
  }

  private String getHeader() {
    return "Rental Record for " + name + "\n";
  }

  private String getBody() {
    return rentals.stream().map(this::bodyLine).collect(joining());
  }

  private String bodyLine(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.price() + "\n";
  }

  private String getFooter() {
    return "Amount owed is " + getTotalPrice() + "\n" +
           "You earned " + getRenterPoints() + " frequent renter points";
  }

  private double getTotalPrice() {
    return rentals.stream().mapToDouble(Rental::price).sum();
  }

  private int getRenterPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }

}