package videostore.dirty;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(final Movie movie, final int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

	public Movie getMovie() {
        return movie;
    }

	public int calculateFrequentRenterPoints() {
		int frequentRenterPoints = 1;

		boolean isNewRelease = movie.getCategory() == Movie.Category.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	static Map<Movie.Category, Function<Integer, Double>> m;
    static {

	}
	public double calculateMaxAllowedDays() {

	}
	public double computeStuffDepnendingOfType() {

	}
	public double calculateRentalPrice() {
//    	Optional.ofNullable(m.get())
//				.orElseThrow()
		return movie.getCategory().computePrice(daysRented);
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
				throw new IllegalStateException("Unexpected value: " + movie.getCategory());
		}
		return price;
	}
}