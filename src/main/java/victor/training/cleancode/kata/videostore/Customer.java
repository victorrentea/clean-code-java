package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental

		result += rentals.stream()
				.map(movieRental -> "\t" + movieRental.movie().title() + "\t" + movieRental.getRentalPrice() + "\n")
				.collect(Collectors.joining());

		totalAmount += rentals.stream().mapToDouble(MovieRental::getRentalPrice).sum();
		int frequentRenterPoints = rentals.stream().mapToInt(Customer::getFrequentRenterPoints).sum();
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int getFrequentRenterPoints(MovieRental movieRental) {
        if (movieRental.movie().priceCode() == Movie.PriceCode.NEW_RELEASE && movieRental.numDays() > 1) {
			return 2;
		}
		return 1;
	}

}