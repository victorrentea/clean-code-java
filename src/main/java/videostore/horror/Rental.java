package videostore.horror;

import static videostore.horror.Movie.Type.NEW_RELEASE;

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

   public double computePrice() { // Feature Envy
      switch (movie.getType()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return daysRented * 3;
         case CHILDRENS:
            return computeChildrenPrice();
         default:
            throw new IllegalStateException("Unexpected value: " + getMovie().getType());
      }
   }

   private double computeChildrenPrice() {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }

   public int computePoints() {
      int frequentRenterPoints = 1;
      if (movie.getType() == NEW_RELEASE && daysRented >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
