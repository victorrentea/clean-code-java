package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie m, int daysRented) {
		rentals.put(m, daysRented);
	}

	public String getName() {
		return name;
	}

	public String buildStatement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Movie> rentals = this.rentals.keySet().iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			Movie movie = rentals.next();
			int daysRented = this.rentals.get(movie);

			frequentRenterPoints = determineFrequentRenterPoints(frequentRenterPoints, movie, daysRented);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + determineAmount(movie, daysRented) + "\n";
			totalAmount += determineAmount(movie, daysRented);
		}
		result += formatFooter(totalAmount, frequentRenterPoints);
		return result;
	}

	private String formatFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private int determineFrequentRenterPoints(int frequentRenterPoints, Movie movie, int daysRented) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if (movie.getCategory() != null &&
			 (movie.getCategory() == MovieCategory.NEW_RELEASE)
			 && daysRented > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private double determineAmount(Movie movie, int daysRented) {
		double thisAmount = 0;
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
		return thisAmount;
	}
}