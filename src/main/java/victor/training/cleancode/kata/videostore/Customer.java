package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieCategory.NEW_RELEASE;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	public static final int STANDARD_RENTAL_POINTS = 1;
	public static final int NEW_RELEASE_RENTAL_POINTS = 2;
	private String name;
	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String printStatement() {
        return header() + body() + footer();
	}

	private String header() {
		return "Rental Record for " + name + "\n";
	}

	private String footer() {
		int frequentRenterPoints = rentals.stream().mapToInt(Customer::calculateFrequentRentalPoints).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::calculateCosts).sum();
		// add footer lines
		String footer = "Amount owed is " + totalPrice + "\n";
		footer += "You earned " + frequentRenterPoints + " frequent renter points";
		return footer;
	}

	private String body() {
		return rentals.stream()
				.map(rental -> "\t" + rental.movie().title() + "\t" + rental.calculateCosts() + "\n")
				.collect(Collectors.joining());
	}

	private static int calculateFrequentRentalPoints(Rental rental) {
		if (rental.movie().category() == NEW_RELEASE && rental.rentalDays() > 1) {
			return NEW_RELEASE_RENTAL_POINTS;
		}
		return STANDARD_RENTAL_POINTS;
	}
}
