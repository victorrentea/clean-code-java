package videostore.horror;

import videostore.horror.Movie.Category;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public int computeFrequentRenterPoints() {
      boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
      int points = 1;
      if (isNewRelease && daysRented >= 2) {
         points++;
      }
      return points;
   }
}
