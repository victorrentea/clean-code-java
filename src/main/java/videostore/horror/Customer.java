package videostore.horror;

import java.util.*;
class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
}
class Customer {
	private final String name;
	private final List<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalList.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentalList) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			double price = computePrice(movie, daysRented);

			frequentRenterPoints += addFrequentRenterPoints(movie, daysRented);

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int addFrequentRenterPoints(Movie movie, int daysRented) {
		int frequentRenterPoints = 0;
		frequentRenterPoints++;
		if (movie.isNewRelease() && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
	private double computePrice(Movie movie, int daysRented) {
		//return switch (movie.getCategory()) { // nu ai nimic in afara de switch in functie
		//			case REGULAR -> computeRegularPrice(daysRented); //1 line / case
		//			case NEW_RELEASE -> computeNewReleasePrice(daysRented);
		//			case CHILDREN -> computeChildrenPrice(daysRented);
		//			// ai default cu throw
		//		};
		switch (movie.getCategory()) { // nu ai nimic in afara de switch in functie
			case REGULAR:
				return computeRegularPrice(daysRented); //1 line / case
			case NEW_RELEASE:
				return computeNewReleasePrice(daysRented);
			case CHILDREN:
				return computeChildrenPrice(daysRented);
			default:
				throw new IllegalStateException("Unexpected value: " + movie.getCategory()); // ai default cu throw
		}
	}

	private int computeNewReleasePrice(int dr) {
		return dr * 3;
	}

	private double computeChildrenPrice(int dr) {
		double price;
		price = 1.5;
		if (dr > 3)
			price += (dr - 3) * 1.5;
		return price;
	}

	private double computeRegularPrice(int dr) {
		double price;
		price = 2;
		if (dr > 2)
			price += (dr - 2) * 1.5;
		return price;
	}
}
//@Test // buna idee
//void test() {
//	for (Movie.Category value : Movie.Category.values()) {
//		computePrice(new Movie()) sa nu arunce illegalStateEx
//	}
//}
