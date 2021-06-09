package videostore.horror;

import java.util.Objects;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = Objects.requireNonNull(movie);
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public double calculatePrice() {
      double price;
      switch (movie.getCategory()) {
         case REGULAR:
            price = 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price = daysRented * 3;
            break;
         case CHILDREN:
            price = 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + movie.getCategory());
      }
      return price;
   }

   public int calculateBonus() {
      int frequentRenterPoints = 1;
      // add bonus for a two day new release rental
      if ((getMovie().getCategory() == Movie.Category.NEW_RELEASE) && getDaysRented() >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
