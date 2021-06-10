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
//      return movie.calculatePrice(daysRented);

//      return new PriceService().calculatePrice(this); // in mod normal sta afara din entity

//      return movie.getCategory().calculatePrice(daysRented);

//      switch (movie.getCategory()) {
//         case REGULAR:
//            return calculateRegularPrice();
//         case NEW_RELEASE:
//            return computeNewReleasePrice();
//         case CHILDREN:
//            return computeChildrenPrice();
//         default:
//            throw new IllegalArgumentException();
//      }
      // java 17 frate!
      return switch (movie.getCategory()) {
         case REGULAR -> calculateRegularPrice();
         case NEW_RELEASE -> computeNewReleasePrice();
         case CHILDREN -> computeChildrenPrice();
      };
   }
   public int maxAllowedRentDays() {
      switch (movie.getCategory()) {
         case REGULAR: return 5;
         case NEW_RELEASE: return 2;
         case CHILDREN: return 14;
         default: throw new IllegalStateException("Unexpected value: " + movie.getCategory());
      }
   }

   private int computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeChildrenPrice() {
      double price;
      price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private double calculateRegularPrice() {
      double price;
      price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }

   public int calculateBonus() {
      int frequentRenterPoints = 1;
      // add bonus for a two day new release rental
      if ((getMovie().getCategory() == Category.NEW_RELEASE) && getDaysRented() >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
