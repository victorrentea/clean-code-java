package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

public class Customer {
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

	public String statement() {
		return formatHeader() +
				 formatBody() +
				 formatFooter(computeTotalPoints(), computeTotalPrice());
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::getPrice).sum();
	}

	private String formatBody() {
		return rentals.stream().map(this::formatRental).collect(Collectors.joining());
	}

	private String formatRental(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter(int frequentRenterPoints, double totalPrice) {
		return "Amount owed is " + totalPrice + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::calculatePoints).sum();
	}

}