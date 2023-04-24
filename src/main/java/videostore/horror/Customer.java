package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	// preserves order
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

	public String getReceipt() {
		return getReceiptHeader() + getBody() + getReceiptFooter();
	}

	private String getBody() {
		return rentals.stream().map(Customer::getLineItem).collect(Collectors.joining());
	}

	@NotNull
	private static String getLineItem(Rental rental) {
		return "\t" + rental.movie().title() + "\t" + rental.computePrice() + "\n";
	}

	@NotNull
	private String getReceiptFooter() {
		String result = "Amount owed is " + sumPrice() + "\n";
		result += "You earned " + sumEarnedPoints() + " frequent renter points";
		return result;
	}

	@NotNull
	private String getReceiptHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private double sumPrice() {
		return rentals.stream()
				.mapToDouble(Rental::computePrice)
				.sum();
	}

	private int sumEarnedPoints() {
		return rentals.stream()
				.mapToInt(Rental::computeBonusPoints)
				.sum();
	}

}
