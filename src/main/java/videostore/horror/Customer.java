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
	// - poate o mutam intr-un business logic separat
	// de formatarea stringului (poate cu ostructura noua)
	// - switchul din computePrice
	// - sparge forul

	public String createStatement() {

		int frequentRenterPoints = rentals.stream().mapToInt(Rental::computeBonusFrequentRenterPoints).sum();

		double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();


		String statement = formatHeader();

		statement += rentals.stream().map(this::formatStatementLine).collect(Collectors.joining());
		statement += formatFooter(totalPrice, frequentRenterPoints);
		return statement;
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private String formatStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}