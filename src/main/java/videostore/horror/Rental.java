package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.Objects;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = Objects.requireNonNull(movie);
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public int computeRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }

   public double getPrice() {
      switch (movie.getCategory()) {
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDREN:
            return computeChildrenPrice();
         case REGULAR:
            return computeRegularPrice();
         default:
            throw new IllegalStateException("Unexpected value: " + movie.getCategory());
      }
   }

   private double computeChildrenPrice() {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private int computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
}
