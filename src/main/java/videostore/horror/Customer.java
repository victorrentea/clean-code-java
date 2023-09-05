package videostore.horror;


import java.util.LinkedHashMap;
import java.util.Map;

import static videostore.horror.PriceCode.NEW_RELEASE;

class Customer {

	private String name;

	// TODO: Lista de Rental
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String statement() {
		String result = "Rental Record for " + this.name + "\n";
		double totalAmount = 0;
		for (Movie movie : rentals.keySet()) {
			// determine amounts for every line
			int daysRented = rentals.get(movie);
			double thisAmount = calculateAmount(movie, daysRented);
			// show figures line for this rental
			totalAmount += thisAmount;

			result += "\t" + movie.title() + "\t" + thisAmount + "\n";
		}

		int frequentRenterPoints = rentals.keySet().stream()
				.mapToInt(movie -> calculateFrequentRenterPoints(movie, rentals.get(movie)))
				.sum();
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int calculateFrequentRenterPoints(Movie movie, int daysRented) {
//		int frequentRenterPoints = 1;
//		if (movie.priceCode() == NEW_RELEASE && daysRented >= 2) frequentRenterPoints++;
//		return frequentRenterPoints;

		return movie.priceCode() == NEW_RELEASE && daysRented >= 2 ? 2 : 1;
	}

	private double calculateAmount(Movie movie, int daysRented) {
		double thisAmount = 0;
		switch (movie.priceCode()) {
			case REGULAR -> {
				thisAmount += 2;
				if (daysRented > 2)
					thisAmount += (daysRented - 2) * 1.5;
			}
			case NEW_RELEASE -> thisAmount += daysRented * 3;
			case CHILDREN -> {
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
			}
		}
		return thisAmount;
	}
}