package videostore.horror;

import videostore.horror.Movie.Category;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public double computePrice() {
      double thisAmount = 0;
      return switch (getMovie().getCategory()) {
         case REGULAR -> computeRegularMoviePrice(thisAmount);
         case NEW_RELEASE -> daysRented * 3;
         case CHILDREN -> computeChildrenPrice(thisAmount);
      };
   }

   private double computeChildrenPrice(double thisAmount) {
      thisAmount += 1.5;
      if (daysRented > 3) {
         thisAmount += (daysRented - 3) * 1.5;
      }
      return thisAmount;
   }

   private double computeRegularMoviePrice(double thisAmount) {
      thisAmount += 2;
      if (daysRented > 2) {
         thisAmount += (daysRented - 2) * 1.5;
      }
      return thisAmount;
   }

   public int computeRenterPoints() { // Feature Envy
      boolean isNewRelease = getMovie().getCategory() == Category.NEW_RELEASE;
      boolean deservesBonus = isNewRelease && getDaysRented() >= 2;

      return /*DEFAULT_POINTS*/ 1 + (deservesBonus ? /*BONUS_POINTS*/ 1 : 0);

//		@Val :: bellow could be better?
//		int frequentRenterPoints = 1;
//		if (deservesBonus) {
//			frequentRenterPoints++;
//		}
//		return frequentRenterPoints;
   }
}
