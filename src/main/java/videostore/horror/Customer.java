package videostore.horror;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

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
		return generateHeader() + generateBody() + generateFooter();
	}

	private String generateHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String generateBody() {
		return rentals.stream().map(this::formatRental).collect(joining());
	}

	private String generateFooter() {
		return "Amount owed is " + calculateTotalPrice() + "\n" +
				"You earned " + calculateTotalPoints() + " frequent renter points";
	}

	private int calculateTotalPoints() {
		return rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
	}

	private double calculateTotalPrice() {
		return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
	}

	// nu merita dusa in Rental ca e presentation.
	private String formatRental(Rental rental) {
		return "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";
	}
}