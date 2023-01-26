package videostore.horror;

import java.util.Objects;

public class Rental {
  private final Movie movie;
  private final int daysRented;

  Rental(Movie movie, int daysRented) {
    this.movie = Objects.requireNonNull(movie);
    this.daysRented = daysRented;
  }

  // am facut OOP
  public double computePrice() {
    return switch (movie.getPriceCode()) { // switch a devenit EXPRESIE : da rezultat
      case REGULAR -> computeRegularPrice();
      case NEW_RELEASE -> daysRented * 3;
      case CHILDREN -> computeChildrenPrice();
      case ELDERS, BURLACI-> 1;
    };
  }

  public double computeMaxAllowedRentedDays() {
    switch (movie.getPriceCode()) {
      case REGULAR:
        return 4;
      case NEW_RELEASE:
        return 2;
      case CHILDREN:
        return 7;
      default:
        throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
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

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }
}
