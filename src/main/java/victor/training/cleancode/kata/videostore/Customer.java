package victor.training.cleancode.kata.videostore;

import java.util.LinkedList;
import java.util.List;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
  private final String name;
  private final List<Rental> rentals = new LinkedList<>();

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addRental(Movie movie, int days) {
    rentals.add(new Rental(movie, days));
  }

  public Statement statement() {
    double totalRentalAmount = computeTotalAmount();
    int frequentRenterPoints = computeFrequentRenterPoints();
    return new Statement(name, totalRentalAmount, frequentRenterPoints, rentals);
  }

  private double computeTotalAmount() {
    return rentals.stream().mapToDouble(Rental::computePrice).sum();
  }

  private int computeFrequentRenterPoints() {
    int frequentRenterPoints = 0;
    for (Rental rental : rentals) {
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (rental.movie().priceCode() == NEW_RELEASE && rental.days() > 1)
        frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

}