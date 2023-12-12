package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

class Customer {

	// Test Bianca B
	private String name;
	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}


	public String statement() {
		double totalAmount = 0;
		int totalPoints = 0;
		String result = getStatementHeader();
		for (Rental rental : rentals) {
			double thisAmount = rental.getTotalAmount();
			totalPoints=rental.updateTotalPoints(rental, totalPoints);
			result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		result += getStatementFinal(totalAmount, totalPoints);
		return result;
	}

	private String getStatementHeader () {
		return "Rental Record for " + name + "\n";
	}

	private String getStatementFinal (double totalAmount, int frequentRenterPoints) {
		return 	"Amount owed is " + totalAmount + "\n"
			+ "You earned " + frequentRenterPoints + " frequent renter points";
	}
}