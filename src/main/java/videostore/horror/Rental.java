package videostore.horror;

import videostore.horror.Movie.Category;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

//   move compute price into movie and then replace switch with polymorphism
   public double computePrice() {
//      return switch (getMovie().getCategory()) {  java 17
      switch (getMovie().getCategory()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDRENS:
            return computeChildrenPrice();
         default:
            throw new IllegalArgumentException();
      }
   }

   private double computeChildrenPrice() {
      double price;
      price = 1.5;
      if (getDaysRented() > 3)
         price += (getDaysRented() - 3) * 1.5;
      return price;
   }

   private int computeNewReleasePrice() {
      return getDaysRented() * 3;
   }

   private double computeRegularPrice() {
      double price;
      price = 2;
      if (getDaysRented() > 2)
         price += (getDaysRented() - 2) * 1.5;
      return price;
   }


   public boolean isBonusApplicable() {
      return (movie.getCategory() == Category.NEW_RELEASE)
             && getDaysRented() > 1;
   }

   public int computeFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      // add bonus for a two day new release rental
      if (isBonusApplicable()) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
