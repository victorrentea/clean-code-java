package victor.training.cleancode.kata.videostore;

import java.util.*;

import static org.jooq.lambda.Agg.sum;
import static victor.training.cleancode.kata.videostore.enums.MovieType.*;

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
		double totalAmount = 0;
		int frequentRenterPointsTotal;
		String result = "Rental Record for " + getName() + "\n";
		//  iterate each rental
		for (Rental rental : rentals) {
			final Movie each = rental.movie();
			// determine amounts for every line
			double thisAmount = rental.computeAmount();
			// add frequent renter points
			//frequentRenterPointsTotal += rental.getFrequentRenterPoints();
			// show figures line for this rental
			result += moviePrice(each, thisAmount);
			//totalAmount += thisAmount;
		}


		totalAmount =
				rentals.stream().mapToDouble(Rental::computeAmount).sum();

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