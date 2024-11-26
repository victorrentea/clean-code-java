package victor.training.cleancode.kata.videostore;

import java.util.*;

import static victor.training.cleancode.kata.videostore.Category.*;

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
			double thisAmount = 0;
			// determine amounts for every line
			int numberOfRentals = rentals.get(movie);
			switch (movie.category) {
				case REGULAR -> {
					thisAmount += 2;
					if (numberOfRentals > 2)
						thisAmount += (numberOfRentals - 2) * 1.5;
				}
				case NEW_RELEASE -> thisAmount += numberOfRentals * 3;
				case CHILDREN -> {
					thisAmount += 1.5;
					if (numberOfRentals > 3)
						thisAmount += (numberOfRentals - 3) * 1.5;
				}
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getPriceCode() != null &&
				 (movie.getPriceCode() == NEW_RELEASE.getCode())
				 && numberOfRentals > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}