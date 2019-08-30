package videostore.dirty;
class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		if(movie == null){
			throw new IllegalArgumentException();
		}
		//TODO georgeT 1.09.2019 validari
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int computeFrequentRenterPoints() {
		int frequentRenterPoints = 1;

		boolean isNewRelease = movie.getCategory() == Movie.Category.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			frequentRenterPoints++;
		}

		return frequentRenterPoints;
	}

	public double computePrice() {
        double price = 0;
        switch (getMovie().getCategory()) {
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