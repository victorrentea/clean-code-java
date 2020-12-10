package videostore.dirty;
import videostore.dirty.Movie.Category;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<Rental>();

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
			double amount = computeAmount(rental);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental

			// TODO rescriu if-ul : metoda in Rental care sa faca asta.

			if ((rental.getMovie().getCategory() == Category.NEW_RELEASE)
				 && rental.getDaysRented() > 1) {
				frequentRenterPoints++;
			}

			// TODO StringBuilder:
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + amount + "\n";
			totalAmount += amount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";

		return result;
	}

	private double computeAmount(Rental rental) {
		double amount = 0;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				amount += 2;
				if (rental.getDaysRented() > 2)
					amount += (rental.getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				amount += rental.getDaysRented() * 3;
				break;
			case CHILDRENS:
				amount += 1.5;
				if (rental.getDaysRented() > 3)
					amount += (rental.getDaysRented() - 3) * 1.5;
				break;
		}
		return amount;
	}
}