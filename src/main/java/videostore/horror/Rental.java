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
		double price = 0;
		switch (movie.getCategory()) {
		case REGULAR:
			price += 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			return price;
		case NEW_RELEASE:
			price += daysRented * 3;
			return price;
		case CHILDREN:
			price += 1.5;
			if (daysRented > 3)
				price += (daysRented - 3) * 1.5;
			return price;
		default:
			throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
		}
	}

	public int determineRenterPoints() {
		int points = 1;
		if (getMovie().isNewRelease() && getDaysRented() >= 2) {
			points++;
		}
		return points;
	}
}
