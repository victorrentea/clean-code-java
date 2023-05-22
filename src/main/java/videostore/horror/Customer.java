package videostore.horror;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static videostore.horror.Movie.PriceCode.NEW_RELEASE;


class Customer {
	private final String name;
	private final List<MovieRental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new MovieRental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (MovieRental rental : rentals) {
// determine amounts for every line
			int daysRented = rental.daysRented();
			double moviePrice = calculateMoviePrice(rental.movie(), daysRented);
// add frequent renter points
			frequentRenterPoints++;

			if ((rental.movie().priceCode() == NEW_RELEASE)
					&& daysRented >= 2) {
				frequentRenterPoints++;
			}
// show figures line for this rental
			result += "\t" + rental.movie().title() + "\t" + moviePrice + "\n";
			totalAmount += moviePrice;
		}
// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double calculateMoviePrice(Movie each, int dr) {
		double result = 0.0;
		switch (each.priceCode()) {
			case REGULAR:
				result += 2;
				if (dr > 2)
					result += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				result += dr * 3;
				break;
			case CHILDREN:
				result += 1.5;
				if (dr > 3)
					result += (dr - 3) * 1.5;
				break;
		}
		return result;
	}
}