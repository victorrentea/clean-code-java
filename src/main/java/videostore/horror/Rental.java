package videostore.horror;

import videostore.horror.Movie.PriceCode;

import static java.util.Objects.requireNonNull;

public class Rental {
  private final Movie movie;
  private final int daysRented;

  Rental(Movie movie, int daysRented) {
    this.movie = requireNonNull(movie);
    if (daysRented <= 0) {
      throw new IllegalArgumentException("NO");
    }
    this.daysRented = daysRented;
  }

  double computePriceForMovie() {
    //    return switch (movie.getPriceCode()) {
    //      case REGULAR -> computeRegularPrice();
    //      case NEW_RELEASE -> daysRented * 3;
    //      case CHILDREN -> computeChildrenPrice();
    //    };
    //    // our reality
    switch (movie.getPriceCode()) {
      case REGULAR:
        return computeRegularPrice();
      case NEW_RELEASE:
        return daysRented * 3;
      case CHILDREN:
        return computeChildrenPrice();
      default:
        throw new IllegalStateException("Unexpected value: " + getMovie().getPriceCode());
    }
  }

  private double computeChildrenPrice() {
    double price;
    price = 1.5;
    if (daysRented > 3)
      price += (daysRented - 3) * 1.5;
    return price;
  }

  private double computeRegularPrice() {
    double price;
    price = 2;
    if (daysRented > 2)
      price += (daysRented - 2) * 1.5;
    return price;
  }

  public boolean earnsBonus() {
    return movie.getPriceCode() == PriceCode.NEW_RELEASE && daysRented >= 2;
  }

  public Movie getMovie() {
    return movie;
  }

  public int getDaysRented() {
    return daysRented;
  }
}
