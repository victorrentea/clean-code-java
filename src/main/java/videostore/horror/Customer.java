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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie each : rentals.keySet()) {
			double price = 0;
			// determine amounts for each line
			int daysRented = rentals.get(each);
			switch (each.category()) {
				case REGULAR:
					price += 2;
					if (daysRented > 2)
						price += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					price += daysRented * 3;
					break;
				case CHILDRENS:
					price += 1.5;
					if (daysRented > 3)
						price += (daysRented - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.category() != null &&
				 (each.category() == MovieCategory.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}