package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");
		// loop over each movie rental
		for (Movie movie : rentals.keySet()) {
            // determine amounts for every line
			int rentalDuration = rentals.get(movie);
            double thisAmount = calculatePrice(movie.priceCode(), rentalDuration);
            // add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.priceCode() == PriceCode.NEW_RELEASE && rentalDuration > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result.append("\t").append(movie.title()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static double calculatePrice(PriceCode priceCode, int dr) {
		double thisAmount = 0L;

		switch (priceCode) {
			case REGULAR -> {
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
			}
			case NEW_RELEASE -> thisAmount += dr * 3;
			case CHILDRENS -> {
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
			}
		}
		return thisAmount;
	}
}