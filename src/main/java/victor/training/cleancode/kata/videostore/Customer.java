package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.put(rental.movie(), rental.rentalDays());
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
			double thisAmount = 0;
			// determine amounts for every line
			int rentalDays = rentals.get(each);
			switch (each.priceCode()) {
				case REGULAR:
					thisAmount += 2;
					if (rentalDays > 2)
						thisAmount += (rentalDays - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += rentalDays * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (rentalDays > 3)
						thisAmount += (rentalDays - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.priceCode() != null &&
				 (each.priceCode() == MovieCategory.NEW_RELEASE)
				 && rentalDays > 1)
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
