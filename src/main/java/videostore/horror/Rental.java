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
      return movie.getCategory().computePrice(daysRented);
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
