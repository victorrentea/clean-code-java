package videostore.dirty;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {

		// toate calculele
		StatementExportData data = computeData();


		// doar formatare
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private StatementExportData computeData() {
		StatementExportData data = new StatementExportData();
		data.name = name;
		data.rentals = rentals;
		data.totalPrice = computeTotalPrice();
		data.totalPoints = computeTotalPoints();
		return data;
	}

	private String formatBody() {
		return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
				 "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
	}

}