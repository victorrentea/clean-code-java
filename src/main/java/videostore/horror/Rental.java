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
      return switch (movie.getCategory()) {
         case REGULAR -> computeRegularPrice();
         case NEW_RELEASE -> computeNewReleasePrice();
         case CHILDREN -> computeChildrenPrice();
         default -> throw new IllegalArgumentException();
      };
   }


   private int computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeChildrenPrice() {
      double price2 = 1.5;
      if (daysRented > 3) {
         price2 += (daysRented - 3) * 1.5;
      }
      return price2;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2) {
         price += (daysRented - 2) * 1.5;
      }
      return price;
   }
}
