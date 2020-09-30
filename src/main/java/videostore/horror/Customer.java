package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;


// ADvanced: enum cu Function<> ---
// Alexandr: Nu e ok sa calculezi treburi cand si formateze >>

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		return formatHeader() +
				 formatBody() +
				 formatFooter();
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatBody() {
		return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalFrequentRenterPoints() {
		return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
				 "You earned " + computeTotalFrequentRenterPoints() + " frequent renter points";
	}

}