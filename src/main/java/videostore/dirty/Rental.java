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
		return movie.getCategory().computePrice(daysRented);
    }

}