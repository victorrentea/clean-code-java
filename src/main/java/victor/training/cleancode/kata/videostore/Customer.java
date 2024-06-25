package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Customer {
	@Getter
	private final String name;
	private final List<MovieRental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int numDays) {
		rentals.add(new MovieRental(movie, numDays));
	}

	public String statement() {
    return header() + body() + footer();
	}

	private String header() {
		return "Rental Record for " + name + "\n";
	}

	private String footer() {
		return "Amount owed is " + totalPrice() + "\n"
					 + "You earned " + totalPoints() + " frequent renter points";
	}

	private int totalPoints() {
		return rentals.stream().mapToInt(MovieRental::getFrequentRenterPoints).sum();
	}

	private double totalPrice() {
		return rentals.stream().mapToDouble(MovieRental::getRentalPrice).sum();
	}

	private String body() {
		return rentals.stream()
				.map(this::bodyLine)
				.collect(joining());
	}

	private String bodyLine(MovieRental movieRental) {
		return "\t" + movieRental.movie().title() + "\t" + movieRental.getRentalPrice() + "\n";
	}

}