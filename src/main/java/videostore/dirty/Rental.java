package videostore.dirty;

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

	public int determineFrequentRenterPoints() {
		int points = 1;
		if (movie.isNewRelease() && daysRented >= 2) {
			points++;
		}
		return points;
	}

	public double determinePrice() {
		double price = 0;
		switch (getMovie().getCategory()) {
			case REGULAR:
				return computeRegularPrice();
			case NEW_RELEASE:
				return computeNewReleasePrice();
			case CHILDRENS:
				return computeChildrenPrice();
			default:
				throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
		}
	}

	private double computeChildrenPrice() {
		double price = 1.5;
		if (getDaysRented() > 3) {
			price += (getDaysRented() - 3) * 1.5;
		}
		return price;
	}

	private int computeNewReleasePrice() {
		return getDaysRented() * 3;
	}

	private double computeRegularPrice() {
		double price = 2;
		if (getDaysRented() > 2) {
			price += (getDaysRented() - 2) * 1.5;
		}
		// Gigel Torn si eu aicea 10 linii de cod, ca n-am un sa le pun
		return price;
	}
}