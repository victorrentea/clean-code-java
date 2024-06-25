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
		return "Rental Record for " + getName() + "\n"
				+ getStatementLines()
				+ "Amount owed is " + rentals.stream().mapToDouble(MovieRental::getRentalPrice).sum() + "\n"
				+ "You earned " + rentals.stream().mapToInt(MovieRental::getFrequentRenterPoints).sum() + " frequent renter points";
	}

	private String getStatementLines() {
		return rentals.stream()
				.map(movieRental -> "\t" + movieRental.movie().title() + "\t" + movieRental.getRentalPrice() + "\n")
				.collect(Collectors.joining());
	}

}