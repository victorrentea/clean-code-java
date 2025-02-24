package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

class Customer {
	private int id;
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	}

  public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
			switch (each.movieType()) {
				case REGULAR:
					thisAmount += 2;
					if (dr > 2)
						thisAmount += (dr - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += dr * 3;
					break;
				case CHILDREN:
					thisAmount += 1.5;
					if (dr > 3)
						thisAmount += (dr - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ( each.movieType() != null &&
					 ( each.movieType() == NEW_RELEASE)
					 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.title() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}
