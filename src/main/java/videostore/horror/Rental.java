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
		double price = 0;
		switch (movie.getType()) {
		case REGULAR:
			price += 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			return price;
		case NEW_RELEASE:
			return daysRented * 3;
		case CHILDRENS:
			price += 1.5;
			if (daysRented > 3)
				price += (daysRented - 3) * 1.5;
			return price;
		default: throw new IllegalArgumentException(getMovie().getType().name());
		}
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




