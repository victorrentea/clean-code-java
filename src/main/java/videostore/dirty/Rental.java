package videostore.dirty;

import java.util.Objects;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = Objects.requireNonNull(movie);
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	private boolean earnsBonus() { // "Indirection without abstraction"
		return getMovie().getCategory() == Movie.Category.NEW_RELEASE
			   && getDaysRented() >= 2;
	}

	public int getBonusPoints() {
		int bonus = 1;
		if (earnsBonus()) {
			bonus ++;
		}
		return bonus;
	}

	public double getPrice() {
		switch (movie.getCategory()) {
			case REGULAR:
				return getRegularPrice();
			case NEW_RELEASE:
				return getNewReleasePrice();
			case CHILDREN:
				return getChildrenPrice();
			default:
				throw new IllegalStateException("Unexpected value: " + movie.getCategory());
		}
	}

	private double getChildrenPrice() {
		double thisAmount;
		thisAmount = 1.5;
		if (daysRented > 3)
			thisAmount += (daysRented - 3) * 1.5;
		return thisAmount;
	}

	private int getNewReleasePrice() {
		return daysRented * 3;
	}

	private double getRegularPrice() {
		double thisAmount;
		thisAmount = 2;
		if (daysRented > 2)
			thisAmount += (daysRented - 2) * 1.5;
		return thisAmount;
	}
}