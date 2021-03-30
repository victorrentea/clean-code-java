package videostore.horror;

import org.junit.Test;
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
      return movie.getCategory().computePrice(daysRented);
//      switch (getMovie().getCategory()) {
//         case REGULAR:
//            return computeRegularPrice();
//         case NEW_RELEASE:
//            return computeNewReleasePrice();
//         case CHILDRENS:
//            return computeChildrenPrice();
//         default:
//            throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
//      }
   }

   // imagine a unit test doing Movie.Category.values()

   @Test
   public void test() {

      for (Category value : Category.values()) {
         // TODO call the method computePrice for this type of movie: expect non zero, no exception.
      }
   }

//   private double computeChildrenPrice() {
//      double price = 1.5;
//      if (daysRented > 3) {
//         price += (daysRented - 3) * 1.5;
//      }
//      return price;
//   }
//
//   private int computeNewReleasePrice() {
//      return daysRented * 3;
//   }
//
//   private double computeRegularPrice() {
//      double price = 2;
//      if (daysRented > 2) {
//         price += (daysRented - 2) * 1.5;
//      }
//      return price;
//   }

   public int computeRenterPoints() {
      int frequentRenterPoints = 1;
      if (earnsExtraRenterPoint()) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
