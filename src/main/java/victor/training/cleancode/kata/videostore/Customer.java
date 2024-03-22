package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	public static final int STANDARD_RENTAL_POINTS = 1;
	public static final int BONUS_RENTAL_POINTS = 2;
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String statement() {
        return header() + body() + footer();
	}

	private String header() {
		return "Rental Record for " + name + "\n";
	}

	private String footer() {
		int frequentRenterPoints = rentals.stream().mapToInt(Customer::calculateFrequentRentalPoints).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
		// add footer lines
		String footer = "Amount owed is " + totalPrice + "\n";
		footer += "You earned " + frequentRenterPoints + " frequent renter points";
		return footer;
	}

	private String body() {
		return rentals.stream()
				.map(rental -> "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n")
				.collect(Collectors.joining());
	}

	private static int calculateFrequentRentalPoints(Rental rental) {
		if (rental.isEligibleForBonusPoints()) {
			return BONUS_RENTAL_POINTS;
		}
		return STANDARD_RENTAL_POINTS;
	}

}
