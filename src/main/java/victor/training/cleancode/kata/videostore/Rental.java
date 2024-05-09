package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {
  public int getFrequentRenterPoints() {
    // add frequent renter points
    int frequentRenterPoints = 1;
    // add bonus for a two days new release rental
    if (movie.priceCode() == NEW_RELEASE && daysRented >= 2) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

  public double getOwedAmount() {
    return switch (movie().priceCode()) {
      case REGULAR -> getRegularPrice();
      case NEW_RELEASE -> getNewReleasePrice();
      case CHILDRENS -> getChildrenPrice();
    };
  }

  private double getChildrenPrice() {
    double price = 1.5;
    if (daysRented > 3) {
      price += (daysRented - 3) * 1.5;
    }
    return price;
  }

  private int getNewReleasePrice() {
    return daysRented * 3;
  }

  private double getRegularPrice() {
    double price = 2;
    if (daysRented > 2) {
      price += (daysRented - 2) * 1.5;
    }
    return price;
  }
}
