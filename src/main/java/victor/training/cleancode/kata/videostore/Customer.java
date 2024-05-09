package victor.training.cleancode.kata.videostore;

import java.util.*;

import static victor.training.cleancode.kata.videostore.enums.MovieType.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

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
		//  iterate each rental
		for (Movie each : rentals.keySet()) {
			// determine amounts for every line
			int daysRented = rentals.get(each);
			double thisAmount = each.computeAmount(daysRented);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.movieType() != null &&
				 (each.movieType() == NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}


}