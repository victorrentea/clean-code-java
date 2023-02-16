package videostore.horror;

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

  public Movie getMovie() {
    return movie;
  }

  public int getDaysRented() {
    return daysRented;
  }
}
