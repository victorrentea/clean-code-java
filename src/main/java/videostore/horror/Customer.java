package videostore.horror;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}

	public String getReceipt() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			// determine amounts for movie line
			int daysRented = rentals.get(movie);
			double price = movie.category().computePrice(daysRented);

			frequentRenterPoints += computeBonusPoints(daysRented, movie.category());

			// show figures line for this rental
			result += "\t" + movie.title() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int computeBonusPoints(int daysRented, MovieCategory movieCategory) {
		int frequentRenterPoints = 1;

		if (movieCategory == MovieCategory.NEW_RELEASE && daysRented >= 2)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
