package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysOfRental) {

  public double price() {
    switch (movie.priceCode()) {
      case REGULAR:
        return regularPrice();
      case NEW_RELEASE:
        return daysOfRental * 3;
      case CHILDREN:
        return childrenPrice();
      default:
        throw new IllegalStateException("Unknown price code: " + movie.priceCode());
//      default: return 999999;
    }
  }

  private double childrenPrice() {
    double result;
    result = 1.5;
    if (daysOfRental > 3)
      result += (daysOfRental - 3) * 1.5;
    return result;
  }

  private double regularPrice() {
//    return 2 + Math.max(0, daysOfRental - 2) * 1.5;
    double result = 2;
    if (daysOfRental > 2)
      result += (daysOfRental - 2) * 1.5;
    return result;
  }
}
