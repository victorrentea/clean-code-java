package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.movie.RentedMovie;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<RentedMovie> rentals = new LinkedList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(RentedMovie arg) {
		rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	public String getClientRentalMoviesPrintableStatus() {
		String result = "Rental Record for " + getName() + "\n";
		double totalAmount = rentals.stream().mapToDouble(RentedMovie::getPrice).sum();
		int frequentRenterPoints = rentals.stream().map(RentedMovie::getFrequentRenterPoints).mapToInt(Integer::intValue).sum();
		result += createTitlesAndPricesString();
		result += addFinalLines(totalAmount, frequentRenterPoints);
		return result;
	}

	private String createTitlesAndPricesString() {
		return rentals.stream().map(RentedMovie::getPrintableTitleAndPrice).collect(Collectors.joining("\n"));
	}

	private static String addFinalLines(double totalAmount, int frequentRenterPoints) {
		String result = "\nAmount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}


}