package videostore.horror;

import java.util.*;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

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
		String result = "Rental Record for " + getName() + "\n";
		double totalPrice = 0;

		int frequentRenterPoints = computeTotalPoints();

		for (Rental rental : rentals) {
			double price = rental.calculatePrice();

			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::calculatePoints).sum();
	}

}