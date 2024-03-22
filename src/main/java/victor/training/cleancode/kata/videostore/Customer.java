package victor.training.cleancode.kata.videostore;

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
		// iterate each rental
		for (Movie each : rentals.keySet()) {
			double price = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
			switch (each.getPriceType()) {
				case REGULAR -> price = getRegularPrice(dr);
				case NEW_RELEASE -> price += dr * 3;
				case CHILDRENS -> {
					price += 1.5;
					if (dr > 3)
						price += (dr - 3) * 1.5;
				}
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceType() != null &&
				 (each.getPriceType() == PriceType.NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + price + "\n";
			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double getRegularPrice(int dr) {
		double thisAmount = 2;
		if (dr > 2)
			thisAmount += (dr - 2) * 1.5;
		return thisAmount;
	}
}