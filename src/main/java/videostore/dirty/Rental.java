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

	public double computePrice() {
		double price = 0;

		switch (getMovie().getType()) {
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

	public int computeFrequentRenterPoints() {
		int frequentRenterPoints = 1;
		boolean isNewRelease = movie.getType() == Movie.Type.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2){
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}


}