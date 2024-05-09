package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {

  public double computeAmount() {
    return switch (movie.movieType()) {
      case REGULAR -> cuDeToatePtToti(2, 2);
      case NEW_RELEASE -> daysRented * 3;
      case CHILDREN -> cuDeToatePtToti(1.5, 3);
    };
  }

  private double cuDeToatePtToti(double baseRate, int threshold) {
    double thisAmount = 0;
    thisAmount += baseRate;
    if (daysRented > threshold) {
      thisAmount += (daysRented - threshold) * 1.5;
    }
//    if (daysRented > 6) // RISK: accidental coupling.
    // POATE FI SI FOARTE SMART PASU ASTA DACA BIZU CONFIRMA CA CELE 2 FORMULE RAMAN IN SYNC
//      thisAmount += (daysRented - 6) * 0.7;
    return thisAmount;
  }

  public int getFrequentRenterPoints() {
    int frequentRenterPoints = 1;
    if (/*movie.movieType() != null &&*/
        movie.movieType() == NEW_RELEASE
        && daysRented > 1) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }
}
