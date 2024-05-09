package victor.training.cleancode.kata.videostore;

import java.util.*;

import static victor.training.cleancode.kata.videostore.enums.MovieType.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPointsTotal = 0;
		String result = "Rental Record for " + getName() + "\n";
		//  iterate each rental
		for (Rental rental : rentals) {
			final Movie each = rental.movie();
			// determine amounts for every line
			int daysRented = rental.daysRented();
			double thisAmount = each.computeAmount(daysRented);
			// add frequent renter points
			frequentRenterPointsTotal += rental.getFrequentRenterPoints();
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPointsTotal + " frequent renter points";
		return result;
	}




}