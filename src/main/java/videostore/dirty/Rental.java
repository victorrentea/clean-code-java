package videostore.dirty;

import lombok.NonNull;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = requireNonNull(movie);
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }
}