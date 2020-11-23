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

   // teoretic intr-un service manageuit despsring
   public double calculatePrice() {
      return movie.getCategory().calculatePrice(new PriceService(), daysRented);
   }
}