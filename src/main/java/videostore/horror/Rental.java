package videostore.horror;

public class Rental {
   public static final int MIN_DAYS_FOR_BONUS_FOR_NEW_RELEASE = 2;

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
      switch (movie.getPriceCode()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDRENS:
            price += 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            break;
      }
      return price;
   }

   public int computeEarnedFrequentPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getPriceCode() == PriceCode.NEW_RELEASE;
      if (isNewRelease && daysRented >= MIN_DAYS_FOR_BONUS_FOR_NEW_RELEASE) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }
}
