package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	// preserves order
	private final List<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalList.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String getReceipt() {
		double totalPrice;
		int frequentRenterPoints;
		String result = "Rental Record for " + getName() + "\n";
		frequentRenterPoints = rentalList.stream()
				.mapToInt(Customer::computeBonusPoints)
				.sum();

		totalPrice = rentalList.stream()
				.mapToDouble(Rental::computePrice)
				.sum();

		for (Rental rental : rentalList) {
			result += "\t" + rental.movie().title() + "\t" + rental.computePrice() + "\n";
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int computeBonusPoints(Rental rental) {
		int frequentRenterPoints = 1;

		if (rental.movie().category() == MovieCategory.NEW_RELEASE && rental.daysRented() >= 2)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
