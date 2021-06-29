package videostore.horror;

import static videostore.horror.Movie.Category.NEW_RELEASE;

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
//      return switch (getMovie().getCategory()) {
//         case REGULAR -> computeRegularPrice();
//         case NEW_RELEASE -> computeNewReleasePrice();
//         case CHILDREN -> computeChildrenPrice();
//      };
      switch (getMovie().getCategory()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDREN:
            return computeChildrenPrice();
         default:
            throw new IllegalArgumentException();
      }
   }

   private double computeChildrenPrice() {
      double result = 1.5;
      if (getDaysRented() > 3)
         result += (getDaysRented() - 3) * 1.5;
      return result;
   }

   private double computeNewReleasePrice() {
      return getDaysRented() * 3;
   }

   private double computeRegularPrice() {
      double result = 2;
      if (getDaysRented() > 2)
         result += (getDaysRented() - 2) * 1.5;
      return result;
   }

   public int computeFrequentPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = getMovie().getCategory() == NEW_RELEASE;
      if (isNewRelease && getDaysRented() >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
