package victor.training.cleancode.kata.videostore;

import java.util.*;
// Remove this comment (thi-cao)
class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int daysRented = rentals.get(each);
			thisAmount = getThisAmount(each.priceCode(), thisAmount, daysRented);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.priceCode() != null &&
				 (each.priceCode() == PriceCode.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double getThisAmount(PriceCode each, double thisAmount, int daysRented) {
        switch (each) {
			case REGULAR -> {
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
            }
			case NEW_RELEASE -> thisAmount += daysRented * 3;
            case CHILDRENS -> {
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
            }
        }
		return thisAmount;
	}


}
