package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.*;

import static videostore.horror.Movie.Category.NEW_RELEASE;

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
		int frequentRenterPoints = 0;
		Iterator<Movie> rentalsIterator = rentals.keySet().iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentalsIterator.hasNext()) {
			double thisAmount = 0;
			Movie movie = rentalsIterator.next();
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
			case CHILDREN:
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
				break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			boolean isNewRelease = movie.getCategory() == NEW_RELEASE;
			boolean deservesBonus = isNewRelease && daysRented >= 2;
			if (deservesBonus) {
				frequentRenterPoints++;
			}
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}