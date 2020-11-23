package videostore.dirty;

import lombok.SneakyThrows;

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



   @SneakyThrows
   public double calculatePrice() {
      PriceCalculator priceCalculator = movie.getCategory().getCalculatorClass().newInstance();
      return priceCalculator.computePrice(daysRented);
//      return movie.computePrice(int daysRented); // care e metoda abstract de pe Movie
//       class RegularMovie extends Movie {
      // class NewRegularMovie extends Movie {
//
//      switch (movie.getCategory()) {
//      case REGULAR:
//         return computeRegularPrice();
//      case NEW_RELEASE:
//         return computeNewReleasePrice();
//      case CHILDREN:
//         return computeChildrenPrice();
//      default:
//            throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
//      }
   }
}