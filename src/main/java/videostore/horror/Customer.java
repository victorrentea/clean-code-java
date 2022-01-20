package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

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

	public String generateStatement() { // move to another class with all satteline methods
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {
		return rentals.stream().map(this::formatLine).collect(joining());
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
	}


	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
				 "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private String formatLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}