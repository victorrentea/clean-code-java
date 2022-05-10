package videostore.horror;

import org.junit.jupiter.api.Test;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			int daysRented = rentals.get(movie);
			double price = computePrice(movie, daysRented);

			frequentRenterPoints = addFrequentRenterPoints(frequentRenterPoints, movie, daysRented);

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int addFrequentRenterPoints(int frequentRenterPoints, Movie movie, int daysRented) {
		frequentRenterPoints++;
		if (movie.isNewRelease() && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
	private double computePrice(Movie movie, int daysRented) {
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
