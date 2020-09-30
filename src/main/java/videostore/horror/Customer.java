package videostore.horror;

import java.util.*;
import java.util.Map.Entry;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			int daysRented = rental.getDaysRented();

			double thisAmount = 0;

			switch (rental.getMovie().getCategory()) {
				case REGULAR:
					thisAmount += 2;
					if (daysRented > 2)
						thisAmount += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += daysRented * 3;
					break;
				case CHILDREN:
					thisAmount += 1.5;
					if (daysRented > 3)
						thisAmount += (daysRented - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.getMovie().getCategory() != null &&
				 (rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
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