package victor.training.cleancode.kata.videostore;

import java.util.Objects;

import static victor.training.cleancode.kata.videostore.MoviePricingCategory.NEW_RELEASE;

record RentedMovie(Movie movie, int daysOfRental) {
  RentedMovie {
    Objects.requireNonNull(movie);
    if (daysOfRental <= 0) {
      throw new IllegalArgumentException("The number of rental days cannot be smaller or equals than 0");
    }
  }

  public double computePrice() {
    return switch (movie().pricingCategory()) {
      case REGULAR -> {
        double price = 2;
        if (daysOfRental() > 2)
          price += (daysOfRental() - 2) * 1.5;
        yield price;
      }
      case NEW_RELEASE -> daysOfRental() * 3;
      case CHILDREN -> {
        double price = 1.5;
        if (daysOfRental() > 3)
          price += (daysOfRental() - 3) * 1.5;
        yield price;
      }
    };
  }

  private boolean isEligibleForFrequentRenterBonus() {
    return movie.pricingCategory() == NEW_RELEASE
           && daysOfRental > 1;
  }

  public int getFrequentRenterPoints() {
    if (isEligibleForFrequentRenterBonus()) {
      return 2;
    }
    return 1;
  }
}
