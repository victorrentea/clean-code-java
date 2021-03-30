package videostore.horror;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import videostore.horror.Movie.Category;

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

   public boolean earnsExtraRenterPoint() {
      return getMovie().getCategory() == Category.NEW_RELEASE && getDaysRented() > 1;
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
         case CHILDRENS:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            break;
      }
      return price;
   }

   public int computeRenterPoints() {
      int frequentRenterPoints = 1;
      if (earnsExtraRenterPoint()) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
