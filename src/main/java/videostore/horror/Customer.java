package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	;

	private static int getFrequentRenterPoints(int frequentRenterPoints, Movie movie, int daysRented) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if (movie.getPriceCode() != null &&
			(movie.getPriceCode() == PriceCode.NEW_RELEASE)
			&& daysRented > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	public String getName() {
		return name;
	}

	private static double getPrice(Movie movie, int daysRented) {
		double price = 0;
		switch (movie.getPriceCode()) {
			case REGULAR:
				price += 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += daysRented * 3;
				break;
			case CHILDREN:
				price += 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				break;
		}
		return price;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			// determine amounts for each line
			int daysRented = rentals.get(movie);
			double price = getPrice(movie, daysRented);

			frequentRenterPoints += getFrequentRenterPoints(0, movie, daysRented);

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " freque" +
				  "nt renter points";
		return result;
	}
}