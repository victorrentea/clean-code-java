package videostore.dirty;
class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int computeRenterPoints() {
		int points= 1;
		if (getMovie().isNewRelease() && getDaysRented() >= 2) {
			points++;
		}
		return points;
	}
}