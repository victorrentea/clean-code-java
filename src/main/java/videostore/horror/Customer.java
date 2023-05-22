package videostore.horror;

import java.util.LinkedHashMap;
import java.util.Map;

import static videostore.horror.Movie.PriceCode.NEW_RELEASE;


class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

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
			// determine amounts for every line
			int dr = rentals.get(each);
			thisAmount = calculateMoviePrice(each, thisAmount, dr);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.priceCode() == NEW_RELEASE)
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

	private static double calculateMoviePrice(Movie each, double thisAmount, int dr) {
		switch (each.priceCode()) {
			case REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}