package videostore.horror;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

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
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for each line
			int dr = rentals.get(each);
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
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() != null &&
				 (each.getPriceCode() == Movie.NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}