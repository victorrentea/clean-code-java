package videostore.horror;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

   public void method() throws ParseException, FileNotFoundException {
      new FileReader("a");
      new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-05");
   }

   public double computePrice() {
      //return switch (movie.getType()) {
      //         case REGULAR -> computeRegularPrice();
      //         case NEW_RELEASE -> computeNewReleasePrice();
      //         case CHILDREN -> computeChildrenPrice();
      //         case ELDERS -> -1;
      //      };



      switch (movie.getType()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDREN:
            return computeChildrenPrice();
         default:
            throw new IllegalStateException("Unexpected value: " + movie.getType());
      }
   }

   private double computeChildrenPrice() {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private double computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
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
      return movie.isNewRelease() && getDaysRented() >= 2;
   }

}
