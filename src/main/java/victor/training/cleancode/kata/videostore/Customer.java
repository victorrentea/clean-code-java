package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {
	@Getter
    private String name;
	//	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

    public String statement() {
		double totalAmount = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n").append(getReportSummary());
		// loop over each movie rental
        int frequentRenterPoints = getFrequentRenterPoints(rentals);
		totalAmount = getTotalAmount();

		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private StringBuilder getReportSummary() {
		StringBuilder result = new StringBuilder("");
        // determine amounts for every line
        // show figures line for this rental
        rentals.forEach(rental -> result.append("\t").append(rental.movie().title()).append("\t")
				.append(rental.movie().priceCode().getPrice(rental.daysRented())).append("\n"));
		return result;
	}

	private double getTotalAmount() {
		return rentals.stream().mapToDouble(rental -> rental.movie().priceCode().getPrice(rental.daysRented())).sum();
	}

	private int getFrequentRenterPoints(List<Rental> rentals) {
		return rentals.stream()
				.mapToInt(rental -> (int) rental.movie().priceCode().getFrequentRenterPoints(rental.daysRented()))
				.sum();

	}

}