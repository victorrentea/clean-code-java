package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {
	@Getter
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for %s\n".formatted(getName());
		// iterate each rental
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
            switch (each.priceCode()) {
                case Movie.REGULAR -> {
                    thisAmount += 2;
                    if (dr > 2)
                        thisAmount += (dr - 2) * 1.5;
                }
                case Movie.NEW_RELEASE -> thisAmount += dr * 3;
                case Movie.CHILDRENS -> {
                    thisAmount += 1.5;
                    if (dr > 3)
                        thisAmount += (dr - 3) * 1.5;
                }
            }
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two-day new release rental
			if (each.priceCode() != null &&
				 (each.priceCode() == Movie.NEW_RELEASE)
				 && dr > 1)
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
