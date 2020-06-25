package videostore.horror;

public class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int computeRenterPoints() {
		int points = 1;
		boolean isNewRelease = movie.getPriceCode() == PriceCode.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			points++;
		}
		return points;
	}

	public double computePrice() {
		switch (movie.getPriceCode()) {
			case REGULAR: return computeRegularPrice();
			case NEW_RELEASE: return computeNewReleasePrice();
			case CHILDRENS: return computeChildrenPrice();
			default: throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
		}
	}

	private double computeChildrenPrice() {
		double price = 1.5;
		if (daysRented > 3) {
			price += (daysRented - 3) * 1.5;
		}
		return price;
	}

	private int computeNewReleasePrice() {
		return daysRented * 3;
	}

	private double computeRegularPrice() {
		double price = 2;
		if (daysRented > 2) {
			price += (daysRented - 2) * 1.5;
		}
		return price;
	}
}
