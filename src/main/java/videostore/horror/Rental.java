package videostore.horror;

import static videostore.horror.PriceCode.NEW_RELEASE;

public record Rental(Movie movie, int days) {

  public int calculateFrequentRenterPoints() {
    int points = 1;
    if (movie.priceCode() == NEW_RELEASE && days >= 2) {
      points++;
    }
    return points;
  }

  public double calculatePrice() {
    return switch (movie().priceCode()) {
      case REGULAR -> calculateRegularPrice();
      case NEW_RELEASE -> days * 3;
      case CHILDREN -> calculateChildrenPrice();
    };
  }

  private double calculateChildrenPrice() {
    double result = 1.5;
    if (days > 3) {
      result += (days - 3) * 1.5;
    }
    return result;
  }

  private double calculateRegularPrice() {
    double result = 2;
    if (days > 2) {
      result += (days - 2) * 1.5;
    }
    return result;
  }
}
