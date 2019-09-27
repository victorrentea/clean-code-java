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

	public int addPoints() {
		final int MIN_DAYS_FOR_BONUS = 2;
		int frequentRenterPoints = 1;
		boolean isNewRelease = movie.getType() == Movie.Type.NEW_RELEASE;
		if (isNewRelease && daysRented >= MIN_DAYS_FOR_BONUS) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	double calculatePrice() {
		double price = 0;
		switch (getMovie().getType()) {
		case REGULAR:
			price += 2;
			if (getDaysRented() > 2)
				price += (getDaysRented() - 2) * 1.5;
			break;
		case NEW_RELEASE:
			price += getDaysRented() * 3;
			break;
		case CHILDREN:
			price += 1.5;
			if (getDaysRented() > 3)
				price += (getDaysRented() - 3) * 1.5;
			break;
		}
		return price;
	}
}