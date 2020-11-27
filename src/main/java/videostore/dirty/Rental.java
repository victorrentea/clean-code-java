package videostore.dirty;

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
      switch (getMovie().getCategory()) {
         case REGULAR:
            return determineRegularPrice();
         case NEW_RELEASE:
            return determineNewReleasePrice();
         case CHILDRENS:
            return determineChildrensPrice();
         default:
            throw new IllegalStateException("JDD: speri ca vreun tester va face aceasta exc sa apara: Unexpected value: " + getMovie().getCategory());
      }
   }

   private double determineRegularPrice() {
      double price = 2;
      if (getDaysRented() > 2)
         price += (getDaysRented() - 2) * 1.5;
      return price;
   }

   private int determineNewReleasePrice() {
      return getDaysRented() * 3;
   }

   private double determineChildrensPrice() {
      double price = 1.5;
      if (getDaysRented() > 3)
         price += (getDaysRented() - 3) * 1.5;
      return price;
   }
}