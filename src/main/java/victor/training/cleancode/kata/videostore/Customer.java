package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {
	@Getter
    private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

    public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Movie movie : rentals.keySet()) {
			// determine amounts for every line
			int daysRental = rentals.get(movie);

			double thisAmount = movie.priceCategory().calculateRentalPrice(daysRental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two days new release rental
			if (movie.priceCategory() != null &&
				 (movie.priceCategory() == PriceCategory.NEW_RELEASE)
				 && daysRental > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

}
