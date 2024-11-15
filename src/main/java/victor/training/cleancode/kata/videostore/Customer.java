package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

class Customer {

	@Getter
    private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int duration) {
		rentals.add(new Rental(movie, duration));
	}

    public String statement() {
		return header() +  body() + footer();
	}

	private String footer() {
		String result = "";
		int frequentRenterPoints = rentals.stream()
				.mapToInt(Rental::frequentRenterPoints)
				.sum();

		double totalAmount = rentals.stream()
				.mapToDouble(Rental::price)
				.sum();

		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private String body() {
		return rentals.stream()
				.map(rental -> "\t" + rental.movie().getTitle() + "\t" + rental.price() + "\n").collect(Collectors.joining());
	}

	private String header() {
		return  "Rental Record for " + getName() + "\n";
	}

}