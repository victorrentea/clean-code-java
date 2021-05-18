package videostore.horror;

import java.util.*;

public class Customer {
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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentals) {
			frequentRenterPoints += rental.computeFrequentRenterPoints();
			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
			totalPrice += rental.computePrice();
			// Inline a method call is a
			// - BUG if the return value is different = referential transparency: for same inputs -> same outputs
			// - BUG if it has side effects (counts the number of times it's called)
			// - BAD idea if expensive in time


		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

}