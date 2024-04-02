package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import lombok.Value;

import java.util.*;

import static victor.training.cleancode.kata.videostore.MovieTicketType.NEW_RELEASE;

@Value
class Customer {
	@Getter
	String name;
	Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};


	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate each rental
		for (Movie each : rentals.keySet()) {
			double rentOwnedAmount = 0;
			// determine amounts for every line
			int daysRented = rentals.get(each);
			switch (each.ticketType()) {
				case REGULAR:
					rentOwnedAmount += 2;
					if (daysRented > 2)
						rentOwnedAmount += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					rentOwnedAmount += daysRented * 3;
					break;
				case CHILDREN:
					rentOwnedAmount += 1.5;
					if (daysRented > 3)
						rentOwnedAmount += (daysRented - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.ticketType() == NEW_RELEASE && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + rentOwnedAmount + "\n";
			totalAmount += rentOwnedAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}