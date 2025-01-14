package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysOfRental) {

  public double price() {
    return switch (movie.priceCode()) {
      case REGULAR -> regularPrice();
      case NEW_RELEASE -> daysOfRental * 3;
      case CHILDREN -> childrenPrice();
      // NU PUNE default daca folsesti swithc ca expresie in java 17
      // pt ca compilatorul iti va da eroare daca nu acoperi toate cazurile
//      default -> throw new IllegalStateException("Unknown price code: " + movie.priceCode());
    };
  }

  private double childrenPrice() {
    double result = 1.5;
    if (daysOfRental > 3) {
      result += (daysOfRental - 3) * 1.5;
    }
    return result;
  }

  private double regularPrice() {
//    return 2 + Math.max(0, daysOfRental - 2) * 1.5;
    double result = 2;
    if (daysOfRental > 2) {
      result += (daysOfRental - 2) * 1.5;
    }
    return result;
  }

  public int getFrequentRenterPoints() {
    int frequentRenterPoints = 1;
    if (movie.priceCode() == PriceCode.NEW_RELEASE && daysOfRental > 1) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }
}
