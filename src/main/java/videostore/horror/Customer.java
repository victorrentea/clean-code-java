package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
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


		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {
		return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateBonus).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();

		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}


}