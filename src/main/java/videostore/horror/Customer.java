package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String getReceipt() {
		return getReceiptHeader() + getReceiptBody() + getReceiptFooter();
	}

	@NotNull
	private String getReceiptHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String getReceiptBody() {
		return rentals.stream().map(Customer::getLineItem).collect(Collectors.joining());
	}

	@NotNull
	private static String getLineItem(Rental rental) {
		return "\t" + rental.movie().title() + "\t" + rental.computePrice() + "\n";
	}

	@NotNull
	private String getReceiptFooter() {
		return "Amount owed is " + sumPrice() + "\n" +
			   "You earned " + sumEarnedPoints() + " frequent renter points";
	}

	private double sumPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int sumEarnedPoints() {
		return rentals.stream().mapToInt(Rental::computeBonusPoints).sum();
	}

}
