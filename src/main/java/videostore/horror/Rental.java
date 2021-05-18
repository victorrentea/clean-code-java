package videostore.horror;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import videostore.horror.Movie.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = Objects.requireNonNull(movie);
      if (daysRented <= 0) {
         throw new IllegalArgumentException();
      }
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public double computePrice() {
      double price = 0;
      switch (movie.getType()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
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

   public int computeFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      if (winsBonus()) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }

   private boolean winsBonus() {
      return isNewRelease() && getDaysRented() >= 2;
   }

   private boolean isNewRelease() {
      return movie.getType() == Type.NEW_RELEASE;
   }
}
