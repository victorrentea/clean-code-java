package videostore.horror;

public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public double determinePrice() {
      double price = 0;
      switch (movie.getType()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            break;
      }
      return price;
   }
}
