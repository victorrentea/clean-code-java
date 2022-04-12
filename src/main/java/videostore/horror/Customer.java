package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0; // code smells: acumulatori
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for each line
			int daysRented = rentals.get(movie);
			switch (movie.getCategory()) {
				case REGULAR:
					thisAmount += 2;
					if (daysRented > 2)
						thisAmount += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += daysRented * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (daysRented > 3)
						thisAmount += (daysRented - 3) * 1.5;
					break;
			}
			frequentRenterPoints += addFrequentRenterPoints(movie);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int addFrequentRenterPoints(Movie movie) {
		int daysRented = rentals.get(movie);
		int points = 1;
		if (movie.getCategory() == MovieCategory.NEW_RELEASE && daysRented >= 2) {
			points++;
		}
		return points;
	}
}