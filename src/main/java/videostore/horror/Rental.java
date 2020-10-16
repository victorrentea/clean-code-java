package videostore.horror;

public class Rental {
	private final Movie movie;
	private final int daysRented;
	
	public Rental(Movie movie, int daysRentals) {
		this.movie = movie;
		this.daysRented = daysRentals;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public Movie getMovie() {
		return movie;
	}

	public double calculatePrice() {
		switch (movie.getType()) {
		case REGULAR: return calculateRegularPrice();
		case NEW_RELEASE: return daysRented * 3;
		case CHILDRENS: return calculateChildernPrice();
		default:
			throw new IllegalArgumentException(getMovie().getType().name());
		}
	}
	private double calculateChildernPrice() {
		double price = 0;
		price += 1.5;
		if (daysRented > 3) {
			price += (daysRented - 3) * 1.5;
		}
		return price;
	}
	private double calculateRegularPrice() {
		double price = 0;
		price += 2;
		if (daysRented > 2) {
			price += (daysRented - 2) * 1.5;
		}
		return price;
	}
	public int calculateRenterPoints() {
		int points = 1;
		if (isEligibleForBonusPoints()) {
			points++;
		}
		return points;
	}
	private boolean isEligibleForBonusPoints() {
		return movie.isNewRelease() && daysRented >= 2;
	}
}




