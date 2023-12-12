package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

class Customer {

	private final String name;

	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int noMoviesRented) {
		rentals.put(movie, noMoviesRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int NoOfMoviesRented = rentals.get(each);
			switch (each.getMovieCategory()) {
				case MovieCategory.REGULAR:
					thisAmount += 2;
					if (NoOfMoviesRented > 2)
						thisAmount += (NoOfMoviesRented - 2) * 1.5;
					break;
				case MovieCategory.NEW_RELEASE.priceCode:
					thisAmount += NoOfMoviesRented * 3;
					break;
				case MovieCategory.CHILDREN.priceCode:
					thisAmount += 1.5;
					if (NoOfMoviesRented > 3)
						thisAmount += (NoOfMoviesRented - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getMovieCategory() != null &&
				 (each.getMovieCategory() == Movie.NEW_RELEASE)
				 && NoOfMoviesRented > 1)
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

	public Map<Movie, Integer> getRentals() {
		return rentals;
	}
}