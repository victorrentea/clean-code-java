package videostore.dirty;

import java.util.*;

import static java.util.Objects.requireNonNull;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = requireNonNull(name);
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
		String result = formatHeader();

		int frequentRenterPoints = rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();

		double totalPrice = 0;
		for (Rental rental : rentals) {
			double price = rental.determinePrice();
			result += formatStatementLine(rental, price);
			totalPrice += price;
		}
		return result + formatFooter(totalPrice, frequentRenterPoints);
	}

	private static String formatStatementLine(Rental rental, double price) {
		return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private static String formatFooter(double totalPrice, int frequentRenterPoints) {
		String result = "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

}
