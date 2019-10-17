package videostore.dirty;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		if (movie == null) {
			throw new IllegalArgumentException("Movie cannot be null");
		}
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getFrequentRenterPoints() {
		int frequentRenterPoints = 1;
		boolean isNewRelease = movie.getType() == Movie.Type.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	public double calculatePrice() {
		return movie.getType().calculator.calculatePrice();
//		switch (movie.getType()) {
//			case REGULAR:
//				return calculateRegularPrice();
//			case NEW_RELEASE:
//				return calculateNewReleasePrice();
//			case CHILDREN:
//				return calculateChildrenPrice();
//			default:
//				throw new IllegalStateException("JDD Unexpected value: " + movie.getType());
//		}
	}

	public int getMaxAllowerDaysToRent() {
//		switch (movie.getType()) {
//			case REGULAR:
//				return calculateRegularPrice();
//			case NEW_RELEASE:
//				return calculateNewReleasePrice();
//			case CHILDREN:
//				return calculateChildrenPrice();
//			default:
//				throw new IllegalStateException("JDD Unexpected value: " + movie.getType());
//		}
	}
interface PriceCalculator {
	 double calculatePrice();

	}


}

//@Service
//class PriceCalculatorService {
//	private double calculateRegularPrice() {
//		double price = 0;
//		price += 2;
//		if (daysRented > 2)
//			price += (daysRented - 2) * 1.5;
//		return price;
//	}
//
//	private double calculateChildrenPrice() {
//		double price = 1.5;
//		if (daysRented > 3)
//			price += (daysRented - 3) * 1.5;
//		return price;
//	}
//
//	private int calculateNewReleasePrice() {
//		return daysRented * 3;
//	}
//}



class NewReleasePriceCalculator implements Rental.PriceCalculator {

	@Override
	public double calculatePrice() {
		return 0;
	}
}
