package videostore.dirty;
class Rental {
	private Movie movie;
	private int daysRented;

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
		double price = 0;
		switch (movie.getCategory()) {
			case REGULAR:
				price += 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += daysRented * 3;
				break;
			case CHILDREN:
				price += 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				break;
		}
		return price;
	}
}