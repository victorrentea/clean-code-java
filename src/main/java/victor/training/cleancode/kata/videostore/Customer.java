package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {

	@Getter
    private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int duration) {
		rentals.add(new Rental(movie, duration));
	}

    public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Rental rental : rentals) {
			double thisAmount = 0;
			// determine amounts for every line
			int durationOfRental = rental.duration();
			thisAmount = getPrice(rental.movie(), thisAmount, durationOfRental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.movie().getPriceCode() != null &&
				(rental.movie().getPriceCode() == PriceCategory.NEW_RELEASE)
				&& durationOfRental > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.movie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;

                        }
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double getPrice(Movie each, double thisAmount, int dr) {
		switch (each.getPriceCode()) {
			case REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case CHILDRENS:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}