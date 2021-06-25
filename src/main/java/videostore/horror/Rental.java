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

   public double determineAmount() {
      double result = 0;
      switch (movie.getCategory()) {
         case REGULAR:
            result += 2;
            if (daysRented > 2)
               result += (daysRented - 2) * 1.5;
            break;
         case NEW_RELEASE:
            result += daysRented * 3;
            break;
         case CHILDRENS:
            result += 1.5;
            if (daysRented > 3)
               result += (daysRented - 3) * 1.5;
            break;
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
