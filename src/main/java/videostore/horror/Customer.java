package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String generateStatement() {
		int frequentRenterPoints = computeTotalPoints();
		double totalPrice = computeTotalPrice();

		String result = generateHeader();

		result += rentals.stream().map(this::generateStatementLine).collect(joining());

		result += generateFooter(totalPrice, frequentRenterPoints);
		return res`u`lt;
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
	}

	private String generateHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String generateFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private String generateStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}