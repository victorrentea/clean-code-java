package videostore.horror;

import java.util.LinkedHashMap;
import java.util.Map;

import static videostore.horror.MovieCategory.NEW_RELEASE;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String createStatement() {
		String result = "Rental Record for " + name + "\n";
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		for (Movie movie : rentals.keySet()) {
			// determine amounts for each line
			int daysRented = rentals.get(movie);
			double thisAmount = computeAmount(daysRented, movie.getCategory());
			// add frequent renter points
			frequentRenterPoints += getFrequentRenterPoints(movie, daysRented);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int getFrequentRenterPoints(Movie movie, int daysRented) {
		boolean isEligibleForBonus = movie.getCategory() == NEW_RELEASE && daysRented >= 2;
		return isEligibleForBonus ? 2 : 1;
	}

	private static double computeAmount(int daysRented, MovieCategory movieCategory) {
		double thisAmount = 0;
		switch (movieCategory) {
			case REGULAR:
				thisAmount += 2;
				if (daysRented > 2)
					thisAmount += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += daysRented * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}