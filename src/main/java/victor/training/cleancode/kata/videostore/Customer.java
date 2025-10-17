package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {

	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int baseRentalPrice) {
		rentals.put(m, baseRentalPrice);
	}

	public String getName() {
		return name;
	}

	public String displayRentals() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
		for (Movie movie : rentals.keySet()) {
			// determine amounts for every line
			int rentalDays = rentals.get(movie);
			double movieAmount = computeAmountFromPriceCodeAndRentalDays(movie.pricecode(), rentalDays);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.pricecode() == PriceCode.NEW_RELEASE && rentalDays > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result.append("\t").append(movie.title()).append("\t").append(movieAmount).append("\n");
			totalAmount += movieAmount;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static double computeAmountFromPriceCodeAndRentalDays(PriceCode priceCode, int rentalDays) {
        return switch (priceCode) {
            case REGULAR -> {
                double movieAmount = 2;
                if (rentalDays > 2) {
                    movieAmount += (rentalDays - 2) * 1.5;
                }
				yield movieAmount;
            }
            case NEW_RELEASE -> rentalDays * 3;
            case CHILDRENS -> {
                double movieAmount = 1.5;
                if (rentalDays > 3) {
                    movieAmount += (rentalDays - 3) * 1.5;
                }
				yield movieAmount;
            }
        };
	}
}