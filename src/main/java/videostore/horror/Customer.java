package videostore.horror;

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

	public String generateStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Movie movie : rentals.keySet()) {
			double price = 0;
			// determine amounts for each line
			int daysRented = rentals.get(movie);
			price = computePrice(price, movie, daysRented);
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

	private int addFrequentRenterPoints(int frequentRenterPoints, Movie each, int daysRented) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if (each.getPriceCode() != null &&
			 (each.getPriceCode() == PriceCode.NEW_RELEASE)
			 && daysRented > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private double computePrice(double price, Movie each, int daysRented) {
		switch (each.getPriceCode()) {
		case REGULAR:
			price += 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			break;
		case NEW_RELEASE:
			price += daysRented * 3;
			break;
		case CHILDRENS:
			price += 1.5;
			if (daysRented > 3)
				price += (daysRented - 3) * 1.5;
			break;
		}
		return price;
	}
}