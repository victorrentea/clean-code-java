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

   public double computePrice() {
      double price = 0;
      switch (getMovie().getCategory()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            break;
      }
      return price;
   }
}
