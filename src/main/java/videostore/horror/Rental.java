package videostore.horror;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public double computePrice() { // Feature Envy
      double price = 0;
      switch (movie.getType()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2)
               price += (daysRented - 2) * 1.5;
            return price;
         case NEW_RELEASE:
            return daysRented * 3;
         case CHILDRENS:
            price += 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            return price;
         default:
            throw new IllegalStateException("Unexpected value: " + getMovie().getType());
      }
   }
}
