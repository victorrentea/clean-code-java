package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

class Customer {
	@Getter
    private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

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
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
            switch (each.getPriceCode()) {
                case REGULAR -> {
					thisAmount = getThisAmount(thisAmount, dr, 2);
				}
                case NEW_RELEASE -> thisAmount += dr * 3;
                case CHILDREN -> {
                    thisAmount += 1.5;
                    if (dr > 3)
                        thisAmount += (dr - 3) * 1.5;
                }
            }
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() != null &&
				 (each.getPriceCode() == NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double getThisAmount(double thisAmount, int dr, int thisAmount1) {
		thisAmount += thisAmount1;
		if (dr > thisAmount1)
			thisAmount += (dr - thisAmount1) * 1.5;
		return thisAmount;
	}
}