package videostore.dirty;
import java.util.*;

import static java.lang.System.lineSeparator;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
		double totalAmount = 0;
		String result = "Rental Record for " + getName() + lineSeparator();
		int frequentRenterPoints = 0;

		for (Rental rental : rentals) {
			double thisAmount = calculateAmount(rental);

			frequentRenterPoints += rental.calculateFrequentRenterPoints();

			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					  + thisAmount + lineSeparator();
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + lineSeparator();
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double calculateAmount(Rental rental) {
		double amount;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				amount = 2;
				if (rental.getDaysRented() > 2)
					amount += (rental.getDaysRented() - 2) * 1.5;
				return amount;
			case NEW_RELEASE:
				amount = rental.getDaysRented() * 3;
				return amount;
			case CHILDREN:
				amount = 1.5;
				if (rental.getDaysRented() > 3)
					amount += (rental.getDaysRented() - 3) * 1.5;
				return amount;
			default:
				throw new IllegalStateException("Unexpected value: " + rental.getMovie().getCategory());
		}
	}
}