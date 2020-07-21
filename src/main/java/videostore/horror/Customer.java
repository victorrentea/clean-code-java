package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String createStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentals) {
			double price = rental.determinePrice();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			boolean isNewRelease = rental.getMovie().isNewRelease();
			if (isNewRelease && rental.getDaysRented() >= 2)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}