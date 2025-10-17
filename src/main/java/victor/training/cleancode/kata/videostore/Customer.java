package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {

	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int price) {
		rentals.put(m, price);
	}

	public String getName() {
		return name;
	}

	public String displayRentals() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
		for (Movie each : rentals.keySet()) {
			double movieAmount = 0;
			// determine amounts for every line
			int rentalAmount = rentals.get(each);
			switch (each.pricecode()) {
				case REGULAR:
					movieAmount += 2;
					if (rentalAmount > 2)
						movieAmount += (rentalAmount - 2) * 1.5;
					break;
				case NEW_RELEASE:
					movieAmount += rentalAmount * 3;
					break;
				case CHILDRENS:
					movieAmount += 1.5;
					if (rentalAmount > 3)
						movieAmount += (rentalAmount - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.pricecode() == PriceCode.NEW_RELEASE && rentalAmount > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result.append("\t").append(each.title()).append("\t").append(movieAmount).append("\n");
			totalAmount += movieAmount;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}
}