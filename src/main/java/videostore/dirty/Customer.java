package videostore.dirty;

import java.util.*;

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

	public String generateStatement() {
		String result = createHeader();
		double totalPrice = 0;
		int frequentRenterPoints = 0;

		for (Rental rental : rentals) {
			frequentRenterPoints += rental.calculateFrequentRenterPoints();
			result += createBodyLine(rental);
			totalPrice += rental.calculatePrice();

			// apelez o fct de 2 ori; e ok daca fct este pure and fast.
			// BUG: daca modifica state: eg INSERT INTO, send SMS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			// BUG: daca intoarce altceva a doua oara
			// PROBLEMA:  daca dureaza 1s apelul x2 = SEFU LA USA
		}
		result += createFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String createBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

}