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
		Iterator<Movie> rentals = this.rentals.keySet().iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Movie each = (Movie) rentals.next();
			// determine amounts for each line
			int dr = this.rentals.get(each);
			switch (each.getPriceCode()) {
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
			default:
				throw new IllegalStateException("Unexpected value: " + each.getPriceCode());
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() == Movie.Category.NEW_RELEASE && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}
}