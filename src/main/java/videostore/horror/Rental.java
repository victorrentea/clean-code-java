package videostore.horror;

public class Rental {
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

	public double determinePrice() {
		return movie.getCategory().determinePrice(daysRented);
	}

	public int determineRenterPoints() {
		int points = 1;
		if (getMovie().isNewRelease() && getDaysRented() >= 2) {
			points++;
		}
		return points;
	}
}
