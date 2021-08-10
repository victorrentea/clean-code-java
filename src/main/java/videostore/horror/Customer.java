package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			// determine amounts for each line

			double price = computePrice(movie);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getCategory() != null &&
				 (movie.getCategory() == Category.NEW_RELEASE)
				 && rentals.get(movie) > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			totalPrice += price;

		}
		for (Movie movie : rentals.keySet()) {
			double price = computePrice(movie); // ok BECAUSE
			// BUG 1: Side effects (eg INSERT in DB)
			// BUG 2: if the function returns different rsults for the same param -> if it's not REFERENTIAL TRANSPARENT.
			// FAST
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computePrice(Movie movie) {
		int daysRented = rentals.get(movie);
		double price = 0;
		switch (movie.getCategory()) {
			case REGULAR:
				price += 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += daysRented * 3;
				break;
			case CHILDRENS:
				price += 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				break;
		}
		return price;
	}
}