package videostore.dirty;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

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
		return formatHeader()
			   + formatBody()
			   + formatFooter();
	}

	private String formatBody() {
		return rentals.stream()
				.map(this::formatStatementLine)
				.collect(joining());
	}
	// what is wrong if we call a function f() twice?
			// BUG#1 "side effect" if the function CHANGES STATE ("side effect") eg f() {counter++;}
			// BUG#2 NOT "referential transparent" if the function returns a differnt value the second tiem you call it
			// ISSUE? Performance
			// is it thread safe? << ask this only if your flow is multithreaded
			// 		 NO SIDE EFFECTS ===> thread safe.

	private String formatStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + getTotalPrice() + "\n" +
			   "You earned " + getTotalPoints() + " frequent renter points";
	}

	private double getTotalPrice() {
		return rentals.stream().mapToDouble(Rental::determinePrice).sum();
	}

	private int getTotalPoints() {
		return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
	}

}
