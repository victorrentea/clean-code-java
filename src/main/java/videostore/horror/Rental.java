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
      double price = 0;
      switch (getMovie().getCategory()) {
         case REGULAR:
            price += 2;
            if (getDaysRented() > 2)
               price += (getDaysRented() - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price += getDaysRented() * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (getDaysRented() > 3)
               price += (getDaysRented() - 3) * 1.5;
            break;
      }
      return price;
   }

   public int calculateRenterPoints() {
      int result = 1;
      boolean isNewRelease = getMovie().getCategory() == NEW_RELEASE;
      if (isNewRelease && getDaysRented() >= 2) {
         result ++;
      }
      return result;
   }
}
