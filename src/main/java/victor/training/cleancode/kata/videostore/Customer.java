package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
	@Getter
    private String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

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
		// loop over each movie rental
		Set<Movie> movies = rentals.keySet();
		for (Movie each : movies) {
			double price=0;
			// determine amounts for every line
			int discountRate = rentals.get(each);
			price = each.getPrice(discountRate);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.priceCode() != null &&
				 (each.priceCode() == NEW_RELEASE)
				 && discountRate > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + price + "\n";
			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

}