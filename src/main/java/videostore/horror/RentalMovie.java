package videostore.horror;

// replace switch with polymorphism:
//   1) abstract class Movie { abstract computePrice(days);} class RegularMovie extend Movie {computePrice()}
    //    - i will introduce extends between data structures only if
        // subtypes have extra attributes (eg NewReleaseMovie extend Movie { int howRecent; }
    // but if I have to twist just some behaviour:
// 2) abstract enum (java 8,11)
      // CONS: logic in enum ?!?!, static behavior
// 3) enhanced switch (java 17)

public class RentalMovie {
  private final Movie movie;
  private final int rentalDays;

  public RentalMovie(Movie movie, int rentalDays) {
    this.movie = movie;
    this.rentalDays = rentalDays;
  }

  public Movie getMovie() {
    return movie;
  }

  double calculateMoviePrice() {
    return movie.getPriceFactor().computePrice(rentalDays);
//    switch (movie.getPriceFactor()) {
//      case REGULAR:
//        return calculateRegularPrice();
//      case NEW_RELEASE:
//        return calculateNewReleasePrice();
//      case CHILDREN:
//        return calculateChildrenPrice();
//      default: throw new IllegalStateException("Unexpected value: " + movie.getPriceFactor());
//    }
  }

  private double calculateChildrenPrice() {
    double price;
    price = 1.5;
    if (rentalDays > 3)
      price += (rentalDays - 3) * 1.5;
    return price;
  }

  private int calculateNewReleasePrice() {
    return rentalDays * 3;
  }

  private double calculateRegularPrice() {
    double price;
    price = 2;
    if (rentalDays > 2)
      price += (rentalDays - 2) * 1.5;
    return price;
  }

  int calculateRenterPoints() {
    int result = 1;
    boolean isNewRelease = movie.getPriceFactor() == PriceFactor.NEW_RELEASE;
    if (isNewRelease && rentalDays >= 2) {
      result++;
    }
    return result;
  }
}
