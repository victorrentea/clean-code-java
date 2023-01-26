package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.Objects;

public class Rental {
  private final Movie movie;
  private final int daysRented;

  Rental(Movie movie, int daysRented) {
    this.movie = Objects.requireNonNull(movie);
    this.daysRented = daysRented;
  }

  public int computeRenterPoints() {
    int frequentRenterPoints = 1;
    boolean isNewRelease = getMovie().getPriceCode() == PriceCode.NEW_RELEASE;
    if (isNewRelease && getDaysRented() >= 2) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

  // am facut OOP
  public double computePrice() {
    return movie.getPriceCode()
            .priceFormula.apply(daysRented);
    //    switch (movie.getPriceCode()) {
//      case REGULAR:
//        return computeRegularPrice();
//      case NEW_RELEASE:
//        return daysRented * 3;
//      case CHILDREN:
//        return computeChildrenPrice();
//      case ELDERS: return 1;
//      default:
//        throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
        // Switch rule #1 mai bine exceptie decat bani pierduti
    }

    // java 17 ... a dream ... prin 2025
    //   return switch (movie.getPriceCode()) { // switch a devenit EXPRESIE : da rezultat
    //      case REGULAR -> computeRegularPrice();
    //      case NEW_RELEASE -> daysRented * 3;
    //      case CHILDREN -> computeChildrenPrice();
    //      case ELDERS, BURLACI-> 1;
    //    };
//  }

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

  public static double computeRegularPrice(int daysRented) {
    double price = 2;
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
