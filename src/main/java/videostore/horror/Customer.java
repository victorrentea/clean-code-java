package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental e) {
		rentals.add(e);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
		return generateHeader()
				 + generateBody()
				 + generateFooter();
	}

	private String generateBody() {
		return rentals.stream().map(rental -> generateStatementLine(rental)).collect(joining());
	}

	private String generateStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String generateHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeBonus).sum();
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private String generateFooter() {
		int frequentRenterPoints = computeTotalPoints();
		double totalPrice = computeTotalPrice();
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}

}