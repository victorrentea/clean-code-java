package videostore.dirty;

import static java.util.Objects.requireNonNull;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = requireNonNull(movie);
		if (daysRented <= 0) {
			throw new IllegalArgumentException("Negative days");
		}
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

//	private static final String SPACE = " "; // the final frontier
	public int calculateFrequentRenterPoints() {
		int frequentRenterPoints= 1;
		boolean isNewRelease = getMovie().getCategory() == MovieCategory.NEW_RELEASE; // explanatory variable
		if (isNewRelease && getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	public double calculateAmount() {
		double amount;
		switch (getMovie().getCategory()) {
			case REGULAR:
				amount = 2;
				if (getDaysRented() > 2)
					amount += (getDaysRented() - 2) * 1.5;
				return amount;
			case NEW_RELEASE:
				amount = getDaysRented() * 3;
				return amount;
			case CHILDREN:
				amount = 1.5;
				if (getDaysRented() > 3)
					amount += (getDaysRented() - 3) * 1.5;
				return amount;
			default:
				throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
		}
	}
}