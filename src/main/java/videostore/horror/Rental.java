package videostore.horror;

import videostore.horror.Movie.Category;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int computeFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }

   public double computePrice() {
      switch (movie.getCategory()) {
         case REGULAR:
            return computeRegularPrice(daysRented);
         case NEW_RELEASE:
            return computeNewReleasePrice(daysRented);
         case CHILDREN:
            return computeChildrenPrice(daysRented);
         default:
            throw new IllegalArgumentException();
      }
   }


   private static int computeNewReleasePrice(int daysRented) {
      return daysRented * 3;
   }

   private static double computeChildrenPrice(int daysRented) {
      double price2 = 1.5;
      if (daysRented > 3) {
         price2 += (daysRented - 3) * 1.5;
      }
      return price2;
   }

   private static double computeRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2) {
         price += (daysRented - 2) * 1.5;
      }
      return price;
   }
}
