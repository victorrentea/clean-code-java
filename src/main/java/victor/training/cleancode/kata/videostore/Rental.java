package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysOfRental) {

  public double getPrice() {
    double thisAmount = 0;
    switch (movie.priceCode()) {
      case REGULAR:
        thisAmount += 2;
        if (daysOfRental > 2)
          thisAmount += (daysOfRental - 2) * 1.5;
        return thisAmount;
      case NEW_RELEASE:
        thisAmount += daysOfRental * 3;
        return thisAmount;
      case CHILDREN:
        thisAmount += 1.5;
        if (daysOfRental > 3)
          thisAmount += (daysOfRental - 3) * 1.5;
        return thisAmount;
    }
    return thisAmount;
  }
}
