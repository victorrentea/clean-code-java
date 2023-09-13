package videostore.horror;

import static videostore.horror.PriceCode.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {
  public int calculatePoints() {
      int frequentRenterPoints = 1;
      if (movie().priceCode() == NEW_RELEASE && daysRented() >= 2) {
        frequentRenterPoints++;
      }
      return frequentRenterPoints;
  }

  public double calculatePrice() {
    return switch (movie.priceCode()) { // WTF? switch intoarce valoare este expersie ->
      case REGULAR -> calculateRegularPrice();
      case NEW_RELEASE -> daysRented * 3;
      case CHILDREN -> calculateChildrenPrice();
      // din java 17 default este de evitat cand faci "return switch(enum)"
//      default -> throw new IllegalStateException("Unexpected value: " + movie.priceCode());
    };
  }

  private double calculateChildrenPrice() {
    double price = 1.5;
    if (daysRented > 3)
      price += (daysRented - 3) * 1.5;
    return price;
  }

  private double calculateRegularPrice() {
    return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
  }
}
