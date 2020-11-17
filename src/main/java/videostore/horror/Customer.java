package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
	private final String name;

	private final List<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentalList.add(rental);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
		return generateHeader() +
				 generateBody() +
				 generateFooter();
	}

	private String generateBody() {
		return rentalList.stream().map(this::generateBodyLine).collect(joining());
	}

	private String generateBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}


	private String generateHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String generateFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				 + "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private int computeTotalPoints() {
		return rentalList.stream().mapToInt(Rental::computeEarnedFrequentPoints).sum();
	}

	private double computeTotalPrice() {
		return rentalList.stream().mapToDouble(Rental::computePrice).sum();
	}


}