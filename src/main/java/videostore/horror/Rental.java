package videostore.horror;

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

   public double computePrice() { // feature envy
      return switch (movie.getCategory()) {
         case REGULAR -> computeRegularPrice();
         case NEW_RELEASE -> computeNewReleasePrice();
         case CHILDREN -> computeChildrenPrice();
      };
   }

   private double computeChildrenPrice() {
      double price;
      price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private int computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }

   public int computeBonus() {
      int frequentRenterPoints = 1;
      // add bonus for a two day new release rental
      boolean isNewRelease = getMovie().getCategory() == Movie.Category.NEW_RELEASE;
      if (isNewRelease && getDaysRented() >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
