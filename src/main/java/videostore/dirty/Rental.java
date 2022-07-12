package videostore.dirty;

import java.util.Objects;

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
}