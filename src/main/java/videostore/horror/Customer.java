package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

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
		return rentals.stream().map(this::formatBodyLine).collect(joining());
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				 + "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computePoints).sum();
	}
	// "separation by layers of abstraction" -- codul dintr-o metoda trebuie sa fie aprox la acelasi nivel de detaliu

}