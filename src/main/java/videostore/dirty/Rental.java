package videostore.dirty;

class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int computeFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      if (getMovie().isNewRelease() && getDaysRented() >= 2) {
         frequentRenterPoints++;
      }
      return frequentRenterPoints;
   }

   public double computePrice() {
      double price = 0;
      switch (getMovie().getPriceCategory()) {
         case REGULAR:
            price += 2;
            if (getDaysRented() > 2)
               price += (getDaysRented() - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price += getDaysRented() * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (getDaysRented() > 3)
               price += (getDaysRented() - 3) * 1.5;
            break;
      }
      return price;
   }
}