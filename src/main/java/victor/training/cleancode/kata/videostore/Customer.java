package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
	private String name;
	private List<MovieRental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int numDays) {
		rentals.add(new MovieRental(movie, numDays));
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder();
		result.append("Rental Record for ").append(getName()).append("\n");
		// loop over each movie rental
		for (MovieRental movieRental : rentals) {
			frequentRenterPoints = calculateFrequentRenterPoints(movieRental, frequentRenterPoints);
			Movie movie = movieRental.movie();
			double rentalPrice = movieRental.getRentalPrice();
			result.append("\t").append(movie.title()).append("\t").append(rentalPrice).append("\n");
			totalAmount += rentalPrice;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static int calculateFrequentRenterPoints(MovieRental movieRental, int frequentRenterPoints) {
        if (movieRental.movie().priceCode() == Movie.PriceCode.NEW_RELEASE && movieRental.numDays() > 1) {
			return frequentRenterPoints + 2;
		}
		return frequentRenterPoints + 1;
	}

}