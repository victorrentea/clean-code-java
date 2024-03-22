package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {
	@Getter
	private final String name;
	private final List<Rental> rentals = new LinkedList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for %s\n".formatted(getName()));
		// iterate each rental
		for(Rental rental : rentals) {
			// determine amounts for every line
			double price = getPrice(rental.movie(), rental.days());
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two-day new release rental
			if (rental.movie().priceCode() == PriceCode.NEW_RELEASE && rental.days() > 1) {
				frequentRenterPoints++;
			}
			// show figures line for this rental
			result.append("\t").append(rental.movie().title()).append("\t").append(price).append("\n");
			totalAmount += price;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static double getPrice(Movie movie, int rentalDays) {
		double thisAmount = 0;
		switch (movie.priceCode()) {
			case REGULAR -> {
				thisAmount += 2;
				if (rentalDays > 2)
					thisAmount += (rentalDays - 2) * 1.5;
			}
			case NEW_RELEASE -> thisAmount += rentalDays * 3;
			case CHILDRENS -> {
				thisAmount += 1.5;
				if (rentalDays > 3)
					thisAmount += (rentalDays - 3) * 1.5;
			}
		}
		return thisAmount;
	}
}
