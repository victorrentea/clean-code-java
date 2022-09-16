package videostore.dirty;

import java.util.Objects;

public class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = Objects.requireNonNull(movie);
		if (daysRented <= 0) {
			throw new IllegalArgumentException();
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