package videostore.horror;

import java.util.*;
import java.util.Map.Entry;

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

	public String createStatement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		// header
		String result = "Rental Record for " + name + "\n";

		// Ai o mapa pe care doar iterezi pe intrari. Nicodata nu faci get() dupa o cheie arbitrara
		// ===> iti lipseste o abstractie (clasa) pentru a putea itera pe o Lista
		for (Entry<Movie, Integer> entry : rentals.entrySet()) {
			Movie movie = entry.getKey();
			Integer daysRented = entry.getValue();

			double thisAmount = 0;
			// determine amounts for each line

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
			default:
				throw new IllegalStateException("Unexpected value: " + movie.getCategory());
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getCategory() == Movie.Category.NEW_RELEASE && daysRented > 1)
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