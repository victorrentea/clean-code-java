package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, Integer daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate each rental
		for (Movie movie : rentals.keySet()) {
			double amount = 0;
			// determine amounts for every line
			int daysRented = rentals.get(movie);
			switch (movie.category) {
				case REGULAR:
					amount += 2;
					if (daysRented > 2)
						amount += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					amount += daysRented * 3;
					break;
				case CHILDREN:
					amount += 1.5;
					if (daysRented > 3)
						amount += (daysRented - 3) * 1.5;
					break;
			}


			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getPriceCode() != null &&
				 (movie.getPriceCode() == Category.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + amount + "\n";
			totalAmount += amount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";

		return result;

	}
}
