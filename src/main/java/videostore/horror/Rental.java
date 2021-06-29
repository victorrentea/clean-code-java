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
      double result = 0.0;
      switch (getMovie().getCategory()) {
         case REGULAR:
            result += 2;
            if (getDaysRented() > 2)
               result += (getDaysRented() - 2) * 1.5;
            break;
         case NEW_RELEASE:
            result += getDaysRented() * 3;
            break;
         case CHILDREN:
            result += 1.5;
            if (getDaysRented() > 3)
               result += (getDaysRented() - 3) * 1.5;
            break;
      }
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
