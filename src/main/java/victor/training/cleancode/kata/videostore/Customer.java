package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Movie movie : rentals.keySet()) {
			// determine amounts for every line
			int daysRented = rentals.get(movie);

			double thisAmount = movie.priceCode().calculateAmount(daysRented);

			// add frequent renter points
			frequentRenterPoints += calculateFrequentRentalPoints(movie.priceCode(), daysRented);

			// show figures line for this rental
			result += "\t" + movie.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int calculateFrequentRentalPoints(Movie.PriceCode priceCode, int daysRented) {
		return priceCode == Movie.PriceCode.NEW_RELEASE && daysRented > 1 ? 2 : 1;
	}
	
}