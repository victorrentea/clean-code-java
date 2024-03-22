package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
class Customer {
	@Getter
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public void addRental(Movie movie, int rental) {
		rentals.put(movie, rental);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate each rental
		for (Movie movie : rentals.keySet()) {
			double price = 0;
			// determine amounts for every line
			int rentalValue = rentals.get(movie);
			price = getPrice(movie, rentalValue);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getPriceType() == PriceType.NEW_RELEASE && rentalValue > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double getPrice(Movie movie, int rentalValue) {
		return switch (movie.getPriceType()) {
			case REGULAR -> getRegularPrice(rentalValue);
			case NEW_RELEASE -> rentalValue * 3;
			case CHILDRENS -> getChildrenPrice(rentalValue);
		};
    }

	private static double getChildrenPrice(int rentalValue) {
		return (rentalValue > 3) ? (1.5d + (rentalValue - 3) * 1.5) : 1.5d;
	}

	private static double getRegularPrice(int rentalValue) {
		return (rentalValue > 2) ? (2 + (rentalValue - 2) * 1.5) : 2d;
	}
}