package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static videostore.horror.Movie.Type.NEW_RELEASE;

class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

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

	private String formatBody() {
		return rentals.stream().map(this::formatBodyLine).collect(joining());
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				 + "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(this::computePoints).sum();
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}
	// "separation by layers of abstraction" -- codul dintr-o metoda trebuie sa fie aprox la acelasi nivel de detaliu

	private int computePoints(Rental rental) {
		int frequentRenterPoints = 0;
		frequentRenterPoints++;
		if (rental.getMovie().getType() == NEW_RELEASE && rental.getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

}