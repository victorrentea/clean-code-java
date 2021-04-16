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

   public double getPrice() {
      switch (movie.getGenre()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDREN:
            return computeChildrenPrice();
         default:
            throw new IllegalStateException("Unexpected value: " + movie.getGenre());
      }
   }

   private double computeChildrenPrice() {
      double price = 0;
      price += 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   private double computeNewReleasePrice() {
      double price = 0;
      price += daysRented * 3;
      return price;
   }

   private double computeRegularPrice() {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }

   public int calculatePoints() {
      int frequentRenterPoints = 1;
      if (movie.isNewRelease() && daysRented >= 2) {
         frequentRenterPoints ++;
      }
      return frequentRenterPoints;
   }

   //   public int calculatePointGeek() {
//      return movie.getGenre() == Movie.Genre.NEW_RELEASE && daysRented > 1 ? 2 : 1;
//   }
}
