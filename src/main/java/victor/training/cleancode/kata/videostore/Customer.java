package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

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
		String result = "";
		for (Rental rental : rentals) {
			result += "\t" + rental.movie().getTitle() + "\t" + rental.price() + "\n";
		}
		return result;
	}

	private String header() {
		String result = "Rental Record for " + getName() + "\n";
		return result;
	}

}