package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.PriceCategory.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {
  double amount() {
    return movie.priceCategory().calculateRentalPrice(daysRented);
  }
  public static double getRentalCost(final int daysRented, final double rentalCost, final int maxRentDays) {
    return getCost(daysRented, rentalCost, maxRentDays);
  }

  private static double getCost(final int daysRented,
                                double rentalCost, final int maxRentDays) {
    if ( daysRented > maxRentDays) {
      rentalCost += ( daysRented - maxRentDays) * rentalCost;
    }
    return rentalCost;
  }
  int renterPoints() {
    int points = 1;
    if (movie.priceCategory() == NEW_RELEASE && daysRented >= 2) {
      points++;
    }
    return points;
  }
}
