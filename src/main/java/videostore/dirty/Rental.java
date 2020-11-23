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

   public int calculateFrequentRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getCategory() == MovieCategory.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints++; // bonus
      }
      return frequentRenterPoints;
   }

   public double calculatePrice() {
      double price = 0;
      switch (movie.getCategory()) {
      case REGULAR:
         price += 2;
         if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
         break;
      case NEW_RELEASE:
         price += daysRented * 3;
         break;
      case CHILDREN:
         price += 1.5;
         if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
         break;

         default:
            throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
      }
      return price;
   }
}