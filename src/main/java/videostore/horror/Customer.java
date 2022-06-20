package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order (someone was sorry here, felt the need to drop a comment because the code was not explciti)

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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie each : rentals.keySet()) {
			double price = 0;
			// determine amounts for each line
			int daysRented = rentals.get(each);
			price = computePrice(each, daysRented);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() != null &&
				 (each.getPriceCode() == Movie.PriceCode.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computePrice(Movie each, int daysRented) {
		double price = 0;
		switch (each.getPriceCode()) {
			case REGULAR:
				price = computeRegularPrice(daysRented, price);
				break;
			case NEW_RELEASE:
				price += computeNewReleasePrice(daysRented);
				break;
			case CHILDREN:
				price = computeChildrenPrice(daysRented, price);
				break;
		}
		return price;
	}

	private double computeChildrenPrice(int daysRented, double price) {
		price += 1.5;
		if (daysRented > 3) {
			price += (daysRented - 3) * 1.5;
		}
		return price;
	}

	private int computeNewReleasePrice(int daysRented) {
		return daysRented * 3;
	}

	private double computeRegularPrice(int daysRented, double thisAmount) {
		thisAmount += 2;
		if (daysRented > 2) {
			thisAmount += (daysRented - 2) * 1.5;
		}
		return thisAmount;
	}
}