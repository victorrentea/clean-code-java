package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.Random;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public double computePrice() {
      double price;
      switch (movie.getCategory()) {
         case REGULAR:
            price = 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            return price;
         case NEW_RELEASE:
            return daysRented * 3;
         case CHILDREN:
            price = 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            return price;
         default:
            throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
      }
   }

   public int computeBonusFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
