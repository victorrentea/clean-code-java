package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

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

  public String statement() {
    String result = "Rental Record for " + name + "\n";
    for (Rental rental : rentals) {
      double price = rental.price();
      result += "\t" + rental.movie().title() + "\t" + price + "\n";
    }
    result += getFooterLines();
    return result;
  }

  private String getFooterLines() {
    return "Amount owed is " + getTotalPrice() + "\n" +
           "You earned " + getRenterPoints() + " frequent renter points";
  }

  private double getTotalPrice() {
    return rentals.stream()
        .mapToDouble(Rental::price)
        .sum();
  }

  private int getRenterPoints() {
    int frequentRenterPoints = 0;
    for (Rental rental : rentals) {
      // add frequent renter points
      frequentRenterPoints++;
      int daysOfRental = rental.daysOfRental();
      if (rental.movie().priceCode() == PriceCode.NEW_RELEASE && daysOfRental > 1)
        frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }
}