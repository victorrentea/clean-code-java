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

			double thisAmount = calculateAmount(movie, daysRented);

			// add frequent renter points
			frequentRenterPoints += calculateFrequentRentalPoints(movie.getPriceCode(), daysRented);

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int calculateFrequentRentalPoints(int priceCode, int daysRented) {
		return priceCode == Movie.NEW_RELEASE && daysRented > 1 ? 2 : 1;
	}

	private static double calculateAmount(Movie each, int dr) {
		double thisAmount = 0;

		switch (each.getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}