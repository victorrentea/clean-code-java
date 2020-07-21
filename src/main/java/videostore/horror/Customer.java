package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String createStatement() {
		return createHeader() +
			createBody() +
			createFooter();
	}

	private String createBody() {
		return rentals.stream().map(this::createLine).collect(Collectors.joining());
	}

	private String createFooter() {
		return "Amount owed is " + determineTotalPrice() + "\n" +
			"You earned " + determineTotalPoints() + " frequent renter points";
	}

	private int determineTotalPoints() {
		return rentals.stream().mapToInt(this::determineRenterPoints).sum();
	}

	private double determineTotalPrice() {
		return rentals.stream().mapToDouble(Rental::determinePrice).sum();
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.determinePrice() + "\n";
	}

	private int determineRenterPoints(Rental rental) {
		int points = 1;
		if (rental.getMovie().isNewRelease() && rental.getDaysRented() >= 2) {
			points++;
		}
		return points;
	}

}