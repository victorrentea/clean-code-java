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
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String statement() {
		double totalPrice = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate each rental
		int frequentRenterPoints = rentals.stream().mapToInt(Customer::calculateFrequentRentalPoints).sum();
		for (Rental rental : rentals) {
			double price = rental.calculateCosts();
			// show figures line for this rental
			result += "\t" + rental.movie().title() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int calculateFrequentRentalPoints(Rental rental) {
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if (rental.movie().category() == NEW_RELEASE && rental.rentalDays() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
