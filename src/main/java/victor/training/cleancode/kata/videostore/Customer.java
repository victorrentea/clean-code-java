package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int d) {
		rentals.put(movie, d);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
		for (Movie movieRental : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int dr = rentals.get(movieRental);
			switch (movieRental.getMovieType()) {
				case REGULAR:
					thisAmount = calculateForRegular(thisAmount, dr);
					break;
				case NEW_RELEASE:
					thisAmount = calculateForNewRelease(thisAmount, dr);
					break;
				case CHILDREN:
					thisAmount = calculateForChildren(thisAmount, dr);
					break;
			}
			frequentRenterPoints = calculateRenterPoints(movieRental, frequentRenterPoints, dr);
			// show figures line for this rental
			result.append("\t").append(movieRental.getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static int calculateRenterPoints(Movie movieRental, int frequentRenterPoints, int dr) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if ((movieRental.getMovieType() == MovieType.NEW_RELEASE )  && dr > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private static final double DEFAULT_RATE = 1.5;

	private static double calculateForChildren(double thisAmount, int dr) {
		 double newAmount = thisAmount + 1.5;
		if (dr > 3)
			newAmount += (dr - 3) * DEFAULT_RATE;
		return newAmount;
	}

	private static double calculateForNewRelease(double thisAmount, int dr) {

		return thisAmount += dr * 3;

	}

	private static double calculateForRegular(double thisAmount, int dr) {
		thisAmount += 2;
		if (dr > 2)
			thisAmount += (dr - 2) * DEFAULT_RATE;
		return thisAmount;
	}
}
