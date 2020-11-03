package videostore.horror;

import videostore.horror.Movie.Type;

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

   public double determinePrice() {
      double price = 0;
      switch (movie.getType()) {
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
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            break;
      }
      return price;
   }

   public int computeRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getType() == Type.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints += 1;
      }
      return frequentRenterPoints;
   }
}
