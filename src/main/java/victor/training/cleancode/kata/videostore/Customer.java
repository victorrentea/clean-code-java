package victor.training.cleancode.kata.videostore;

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

	public String statement() {

		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		double totalAmount = rentals.stream().map(RentedMovie::getPrice).reduce(0.0, Double::sum); // TODO change
		int frequentRenterPoints = rentals.stream().map(RentedMovie::getFrequentRenterPoints).mapToInt(Integer::intValue).sum();
		result += rentals.stream().map(RentedMovie::getPrintableTitleAndPrice).collect(Collectors.joining("\n"));
		// add footer lines
		result += "\nAmount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}


}