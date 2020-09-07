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
				price += 2;
				if (getDaysRented() > 2) {
					price += (getDaysRented() - 2) * 1.5;
				}
				break;
			case NEW_RELEASE:
				price += getDaysRented() * 3;
				break;
			case CHILDRENS:
				price += 1.5;
				if (getDaysRented() > 3) {
					price += (getDaysRented() - 3) * 1.5;
				}
				break;
		}
		return price;
	}
}