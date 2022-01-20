package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.*;

class Customer {
	private static final int DEFAULT_POINTS = 1;
	private static final int BONUS_POINTS = 1;
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

	public String generateStatement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = formatHeader();
		for (Rental rental : rentals) {

			frequentRenterPoints += rental.computeRenterPoints();
		}
		for (Rental rental : rentals) {
			double price = rental.computePrice();
			result += formatLine(rental, price);
		}
		for (Rental rental : rentals) {
			double price = rental.computePrice();
			// WHEN is it ok to repeat method calls ?
			// > PURE (no side effects , same results) + FAST
			totalAmount += price;
		}
		result += formatFooter(totalAmount, frequentRenterPoints);
		return result;
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private String formatLine(Rental rental, double price) {
		return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
	}

}