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

	public String createStatement() {
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {
		return rentals.stream().map(this::formatStatementLine).collect(Collectors.joining());
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter() {
		double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();
		int totalPoints = rentals.stream().mapToInt(Rental::computeBonusFrequentRenterPoints).sum();
		return "Amount owed is " + totalPrice + "\n" +
				 "You earned " + totalPoints + " frequent renter points";
	}

	private String formatStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}

// TODO vrentea 13-01-2021: de dus gunoiu de prezentare afara in alta clasa ---> StatementFormatter / View/Generator