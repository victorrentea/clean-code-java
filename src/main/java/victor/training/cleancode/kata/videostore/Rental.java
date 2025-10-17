package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MoviePricingCategory.NEW_RELEASE;

public record Rental(Movie movie, int rentalDays) {

  public boolean eligibleForBonus() {
    return movie.pricingCategory() == NEW_RELEASE
           && rentalDays >= 2;
  }

  double computeRentalPrice() {
    return movie.pricingCategory().computeMovieRentalPrice(rentalDays);
  }
}
