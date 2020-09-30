package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

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
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Movie movie : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for each line
			int daysRented = rentals.get(movie);


			/**
			 * abstract class Movie{
			 *    public abstract computeAmount(daysRented);
			 * }
			 * class NewReleaseMovie extends Movie {
			 * 	public computeAmount(daysRented) {
			 * 		return daysRented * 3;
			 * 	}
			 * }
			 *
			 * movie.computeAmount(daysRented); // polymorphic dispatch
			 */

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
			// add bonus for a two day new release rental
			if (movie.getCategory() != null &&
				 (movie.getCategory() == Movie.Category.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t"
						 + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}
}