package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
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
		for (Movie each : rentals.keySet()) {
			// determine amounts for every line
			int daysRental = rentals.get(each);
			double thisAmount = calculateRentalCost(each, daysRental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two days new release rental
			if (each.priceCategory() != null &&
				 (each.priceCategory() == PriceCategory.NEW_RELEASE)
				 && daysRental > 1)
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

	private static double calculateRentalCost(Movie each, int dr)
	{
		double thisAmount = 0;
		switch (each.priceCategory()) {
			case REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case CHILDRENS:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}
