package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieCategory.NEW_RELEASE;

import java.util.*;

import lombok.Getter;

class Customer {
	@Getter
	private String name;
	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate each rental
		for (Rental rental : rentals) {
			double thisAmount = 0;
			// determine amounts for every line
			int rentalDays = rental.rentalDays();
			switch (rental.movie().category()) {
				case REGULAR:
					thisAmount += 2;
					if (rentalDays > 2)
						thisAmount += (rentalDays - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += rentalDays * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (rentalDays > 3)
						thisAmount += (rentalDays - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.movie().category() == NEW_RELEASE && rentalDays > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.movie().title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}
