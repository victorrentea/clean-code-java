package videostore.dirty;

import videostore.dirty.Movie.Category;

import static java.util.Objects.requireNonNull;

class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      if (daysRented <= 0) {
         throw new IllegalArgumentException();
      }
      this.movie = requireNonNull(movie);
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int determineFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      if (movie.isNewRelease() && daysRented >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }

   public double determinePrice() {
      return movie.getCategory().getCalculator().determinePrice(daysRented);
   }


}

interface PriceCalculator {
   double determinePrice(int daysRented);
}

class RegularPriceCalculator implements PriceCalculator {
   public double determinePrice(int daysRented) {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
}

class NewReleasePriceCalculator implements PriceCalculator {
   public double determinePrice(int daysRented) {
      return daysRented * 3;
   }
}

class ChildrenPriceCalculator implements PriceCalculator {
   public double determinePrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }
}
