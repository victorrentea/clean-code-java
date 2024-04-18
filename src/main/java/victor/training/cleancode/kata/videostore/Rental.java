package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

public record Rental(
    Movie movie,
    int daysRented) {

  public double calculatePrice() {
    return switch (movie.priceCode()) {
      case REGULAR -> calculateRegularPrice();
      case NEW_RELEASE -> daysRented * 3;
      case CHILDREN -> calculateChildrenPrice();
    };
  }

  private double calculateChildrenPrice() {
    final int MIN_DAYS_FOR_EXTRA_PRICE = 3;
    double price = 1.5;
    if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
      price += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
    }
    return price;
  }

  private double calculateRegularPrice() {
    final int MIN_DAYS_FOR_EXTRA_PRICE = 2;
    double price = 2;
    if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
      price += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
    }
    return price;
  }

  public int calculateFrequentRenterPoints() {
    int frequentRenterPoints = 1;
    if (movie().priceCode() == NEW_RELEASE && daysRented() >= 2) {
      frequentRenterPoints++; // one MORE point
    }
    return frequentRenterPoints;
  }
}
