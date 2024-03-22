package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieCategory.NEW_RELEASE;

import java.util.*;
import java.util.stream.Collectors;

import lombok.Getter;

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

	public String statement() {
        String result = "Rental Record for " + name + "\n";

		int frequentRenterPoints = rentals.stream().mapToInt(Customer::calculateFrequentRentalPoints).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::calculateCosts).sum();

		result += rentals.stream()
				.map(rental -> "\t" + rental.movie().title() + "\t" + rental.calculateCosts() + "\n")
				.collect(Collectors.joining());

		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int calculateFrequentRentalPoints(Rental rental) {
		if (rental.movie().category() == NEW_RELEASE && rental.rentalDays() > 1) {
			return NEW_RELEASE_RENTAL_POINTS;
		}
		return STANDARD_RENTAL_POINTS;
	}
}
