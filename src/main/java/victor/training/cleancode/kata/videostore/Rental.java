package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieCategory.NEW_RELEASE;
import static victor.training.cleancode.kata.videostore.MovieCategory.REGULAR;

public record Rental (Movie movie, int daysRented) {

    int calculateRewardPoints() {
      return movie.category() == NEW_RELEASE && daysRented > 1 ? 2 : 1;
    }

  String generateStatementLine() {
    return "\t" + movie().title() + "\t" + getCost() + "\n";
  }

  public double getCost() {
    return switch (movie.category()) {
      case REGULAR -> regularCost(daysRented);
      case NEW_RELEASE -> daysRented * 3;
      case CHILDRENS -> childrenPrice(daysRented);
    };
  }

  private static double childrenPrice(int daysRented) {
    double movieCost = 1.5;
    if (daysRented > 3)
      movieCost += (daysRented - 3) * 1.5;
    return movieCost;
  }

  private static double regularCost(int daysRented) {
    double movieCost = 2;
    if (daysRented > 2)
      movieCost += (daysRented - 2) * 1.5;
    return movieCost;
  }
}
