package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class Customer {
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
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatBody() {
		return rentals.stream().map(this::formatRental).collect(Collectors.joining());
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				 + "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeBonusPoints).sum();
	}

	private String formatRental(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}