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

	public double computePrice() {
		switch (movie.getCategory()) {
			case REGULAR:
				return computeRegularPrice();
			case NEW_RELEASE:
				return computeNewReleasePrice();
			case CHILDREN:
				return computeChildrenPrice();
			default:
				throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
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

	public int computeFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = getMovie().getCategory() == Movie.Category.NEW_RELEASE;
        if (isNewRelease && getDaysRented() >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}