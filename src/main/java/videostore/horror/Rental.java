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
      switch (getMovie().getCategory()) {
         case REGULAR:
            thisAmount += 2;
            if (daysRented > 2)
               thisAmount += (daysRented - 2) * 1.5;
            break;
         case NEW_RELEASE:
            thisAmount += daysRented * 3;
            break;
         case CHILDREN:
            thisAmount += 1.5;
            if (daysRented > 3)
               thisAmount += (daysRented - 3) * 1.5;
            break;
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
