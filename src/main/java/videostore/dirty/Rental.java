package videostore.dirty;
class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	private boolean isEligibleForBonusPoints() {
		boolean isNewRelease = movie.getCategory() == Movie.Category.NEW_RELEASE;
		return isNewRelease && daysRented >= 2;
	}

	public int computeFrequentRentalPoints() {
		int frequentRenterPoints = 1;
		if (isEligibleForBonusPoints()) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	public double computePrice() {
		switch (movie.getCategory()) {
			case REGULAR: return computeRegularPrice();
			case NEW_RELEASE: return computeNewReleasePrice();
			case CHILDREN: return  computeChildrenPrice();
			default: throw new IllegalStateException("Unexpected value: " + movie.getCategory());
		}
	}

	private double computeChildrenPrice() {
		double price = 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}

	private int computeNewReleasePrice() {
		return daysRented * 3;
	}

	private double computeRegularPrice() {
		double price = 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}
}