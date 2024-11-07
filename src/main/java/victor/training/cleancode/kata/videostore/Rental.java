package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
  double amount() {
    return movie.priceCategory().calculateRentalPrice(daysRented);
  }

  int renterPoints() {
    int points = 1;
    if (movie().priceCategory() == PriceCategory.NEW_RELEASE && daysRented() > 1) {
      points++;
    }
    return points;
  }
}
