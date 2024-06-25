package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.Movie.PriceCode.NEW_RELEASE;

record MovieRental(Movie movie, int numDays) {

  int getFrequentRenterPoints() {
    if (movie().priceCode() == NEW_RELEASE && numDays() >= 2) {
      return 2;
    }
    return 1;
  }

  double getRentalPrice() {
    return switch (movie.priceCode()) {
      case REGULAR -> getRegularPrice();
      case NEW_RELEASE -> getNewReleasePrice();
      case CHILDREN -> getChildrenPrice();
      case ELDERS -> -1;
      // ANTI_PATTERN TO ADD DEFAULT
//      default -> throw new IllegalArgumentException("Unknown price code: " + movie.priceCode());
    };
  }

  private double getChildrenPrice() {
    return f(1.5, 3);
  }

  private double f(double basePrice, int x) {
    if (numDays > x) {
      return basePrice + (numDays - x) * 1.5;
    }
//    if (numDays > 7) {
//      return basePrice + (numDays - x) * 1.5 + 11;
//    }
    return basePrice;
  }

  private double getNewReleasePrice() {
    return (numDays * 3);
  }

  private double getRegularPrice() {
    return f(2, 2);
  }

}
