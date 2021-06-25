package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String buildStatement() {
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {
		StringBuilder sb = new StringBuilder();
		for (Rental rental : rentals) {
			sb.append(formatLine(rental));
		}
		return sb.toString();
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
	}

	private int computeTotalPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			frequentRenterPoints += rental.determineFrequentRenterPoints();
		}
		return frequentRenterPoints;
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::determinePrice).sum();
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
				 "You earned " + computeTotalPoints() + " frequent renter points";
	}

}