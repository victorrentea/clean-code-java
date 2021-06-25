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

   public double determinePrice() {
      switch (movie.getCategory()) {
         case REGULAR: return computeRegularPrice();
         case NEW_RELEASE: return computeNewReleasePrice();
         case CHILDRENS: return computeChildrenPrice();
         case ELDERS: return computeChildrenPrice();
         default: throw new IllegalStateException("Unexpected value: " + movie.getCategory());
      }
   }

   private int computeNewReleasePrice() {
      return daysRented * 3;
   }

   private double computeChildrenPrice() {
      double result = 1.5;
      if (daysRented > 3) {
         result += (daysRented - 3) * 1.5;
      }
      return result;
   }

   private double computeRegularPrice() {
      double result = 2;
      if (daysRented > 2) {
         result += (daysRented - 2) * 1.5;
      }
      return result;
   }

   public int determineFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getCategory() == MovieCategory.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints ++;
      }
      return frequentRenterPoints;
   }
}
