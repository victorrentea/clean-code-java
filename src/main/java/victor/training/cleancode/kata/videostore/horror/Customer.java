package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

class Customer {

	// Test Bianca B
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}


	public String statement() {
		double totalAmount = 0;
		int totalPoints = 0;
		StringBuilder result = new StringBuilder(getStatementHeader());
		for (Rental rental : rentals) {
			double thisAmount = rental.getTotalAmount();
			totalPoints=rental.updateTotalPoints(rental, totalPoints);
			result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		result.append(getStatementFinal(totalAmount, totalPoints));
		return result.toString();
	}

	private String getStatementHeader () {
		return "Rental Record for " + name + "\n";
	}

	private String getStatementFinal (double totalAmount, int frequentRenterPoints) {
		return 	"Amount owed is " + totalAmount + "\n"
			+ "You earned " + frequentRenterPoints + " frequent renter points";
	}
}