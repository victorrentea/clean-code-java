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
		return new PriceCalculator().computePrice(movie.getCategory(), daysRented);
    }

}

//@SErvice
class PriceCalculator {

//	@Autowired
//	private Repo
	public double computePrice(Movie.Category category, int daysRented) {
		return category.priceAlgo.apply(this, daysRented);
	}

	public double computeChildrenPrice(int daysRented) {
		double price = 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}
	public double computeRegularPrice(int daysRented) {
		double price = 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}
	public double computeNewReleasePrice(int daysRented) {
		return daysRented * 3;
	}
}