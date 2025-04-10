package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

record Rental(Movie movie, int daysRented) {

  public double price() {
    return switch (movie.type()) {
      case REGULAR -> regularPrice();
      case NEW_RELEASE -> daysRented * 3;
      case CHILDREN -> childrenPrice();
//      case BABACI -> 1;
//      default -> throw new IllegalStateException("Unexpected value: " + movie.type());
    };
  }

  private double childrenPrice() {
    double rentalPrice = 1.5;
    if (daysRented > 3) {
      rentalPrice += (daysRented - 3) * 1.5;
    }
    return rentalPrice;
  }

  private double regularPrice() {
    double rentalPrice = 2;
    if (daysRented > 2) {
      rentalPrice += (daysRented - 2) * 1.5;
    }
    return rentalPrice;
  }

  public int frequentRenterPoints() {
    if (movie.type() == NEW_RELEASE && daysRented >= 2) {
      return 2;
    }
    return 1;
  }
}
