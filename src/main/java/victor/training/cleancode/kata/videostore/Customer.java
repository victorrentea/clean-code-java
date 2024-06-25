package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class Customer {
	@Getter
	private final String name;
	private final List<MovieRental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int numDays) {
		rentals.add(new MovieRental(movie, numDays));
	}
	public String statement() {
		double totalAmount = 0;
		StringBuilder result = new StringBuilder();
		result.append("Rental Record for ").append(getName()).append("\n");
		// loop over each movie rental
		for (MovieRental movieRental : rentals) {
			double rentalPrice = movieRental.getRentalPrice();
			result.append("\t").append(movieRental.movie().title()).append("\t").append(rentalPrice).append("\n");
		}
		totalAmount += rentals.stream().mapToDouble(MovieRental::getRentalPrice).sum();
		int frequentRenterPoints = rentals.stream().mapToInt(Customer::getFrequentRenterPoints).sum();
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static int getFrequentRenterPoints(MovieRental movieRental) {
        if (movieRental.movie().priceCode() == Movie.PriceCode.NEW_RELEASE && movieRental.numDays() > 1) {
			return 2;
		}
		return 1;
	}

}