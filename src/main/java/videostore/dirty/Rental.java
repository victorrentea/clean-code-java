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

	public boolean earnsBonus() { // "Indirection without abstraction"
		return getMovie().getCategory() == Movie.Category.NEW_RELEASE
			   && getDaysRented() >= 2;
	}
}