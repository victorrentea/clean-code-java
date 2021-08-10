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

   public double computePrice() {
      double price = 0;
      switch (getMovie().getCategory()) {
         case REGULAR:
            price += 2;
            if (getDaysRented() > 2)
               price += (getDaysRented() - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price += getDaysRented() * 3;
            break;
         case CHILDRENS:
            price += 1.5;
            if (getDaysRented() > 3)
               price += (getDaysRented() - 3) * 1.5;
            break;
      }
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
