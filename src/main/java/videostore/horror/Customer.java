package videostore.horror;

import java.util.*;
import java.util.Map.Entry;

import static videostore.horror.Movie.Type.NEW_RELEASE;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
//		for (Movie each : rentals.keySet()) {
//			int daysRented = rentals.get(each);

		for (Entry<Movie, Integer> entry : rentals.entrySet()) {
			Movie each = entry.getKey();
			int daysRented = entry.getValue();


			double thisAmount = 0;
			// determine amounts for each line
			thisAmount = computeAmount(each, thisAmount, daysRented);

			// add frequent renter points
			frequentRenterPoints++;
			if (deservesBonus(each, daysRented)) {
				frequentRenterPoints++;
			}

			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computeAmount(Movie each, double thisAmount, int daysRented) {
		switch (each.getPriceCode()) {
			case REGULAR:
				thisAmount += 2;
				if (daysRented > 2)
					thisAmount += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += daysRented * 3;
				break;
			case CHILDRENS:
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
				break;
			default: //always
				throw new IllegalStateException("Unexpected value: " + each.getPriceCode());
		}
		return thisAmount;
	}

	private boolean deservesBonus(Movie each, int daysRented) {
		return each.getPriceCode() == NEW_RELEASE && daysRented >= 2;
	}
}