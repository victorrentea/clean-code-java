package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

import static org.jooq.lambda.Agg.sum;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
    int frequentRenterPointsTotal;
		String result = "Rental Record for " + getName() + "\n";
		//  iterate each rental

		result += rentals.stream().map(rental -> moviePrice(rental.movie(), rental.computeAmount())).collect(Collectors.joining());

    double totalAmount = rentals.stream().mapToDouble(Rental::computeAmount).sum();

    frequentRenterPointsTotal =
				rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();

		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPointsTotal + " frequent renter points";
		return result;
	}

	private static String moviePrice(Movie each, double thisAmount) {
		return "\t" + each.title() + "\t" + thisAmount + "\n";
	}


}