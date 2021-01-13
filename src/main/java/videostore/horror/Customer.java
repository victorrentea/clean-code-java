package videostore.horror;

import videostore.horror.Movie.Category;

import java.io.FileInputStream;
import java.util.*;

class Rental {
	private final Movie movie;
	private final int daysRented;
}

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentalsMap = new LinkedHashMap<>(); // preserves order
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalsMap.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}
	// - poate o mutam intr-un business logic separat
	// de formatarea stringului (poate cu ostructura noua)
	// - switchul din computePrice
	// - sparge forul

	// array (lista secventiala dupa index)
	// map (key -> value)

	public String createStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String statement = "Rental Record for " + getName() + "\n";

		for (Movie movie : rentalsMap.keySet()) {
			int daysRented = rentalsMap.get(movie);

			double price = computePrice(movie, daysRented);

			frequentRenterPoints += addFrequentRenterPoints(movie, daysRented);

			// show figures line for this rental
			statement += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		statement += "Amount owed is " + totalPrice + "\n";
		statement += "You earned " + frequentRenterPoints + " frequent renter points";
		return statement;
	}

	private int addFrequentRenterPoints(Movie movie, int daysRented) {
		int frequentRenterPoints = 1;
		boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	private double computePrice(Movie movie, int dr) {
		double price;
		switch (movie.getCategory()) {
			case REGULAR:
				price = 2;
				if (dr > 2)
					price += (dr - 2) * 1.5;
				return price;
			case NEW_RELEASE:
				return dr * 3;
			case CHILDREN:
				price = 1.5;
				if (dr > 3)
					price += (dr - 3) * 1.5;
				return price;
			default:
				throw new IllegalStateException("Unexpected value: " + movie.getCategory());
		}
	}
}