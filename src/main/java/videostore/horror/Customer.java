package videostore.horror;

import static java.util.stream.Collectors.joining;

import java.util.*;
import java.util.Map.Entry;


class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}
	
	// 2) why is this in customer
	// 3) SWITCH - extract from it
	// 4) separate the presentation fro mcomputation : MVC

	public String createStatement() {
		return createHeader() + 
				createBody() + 
				createFooter();
	}

	private String createBody() {
		return rentals.stream().map(this::createBodyLine).collect(joining());
	}

	private String createFooter() {
		return "Amount owed is " + calculateTotalPrice() + "\n" +
				"You earned " + calculateTotalRenterPoints() + " frequent renter points";
	}

	private double calculateTotalPrice() {
		return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
	}

	private int calculateTotalRenterPoints() {
		return rentals.stream().mapToInt(Rental::calculateRenterPoints).sum();
	}

	private String createBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}
}