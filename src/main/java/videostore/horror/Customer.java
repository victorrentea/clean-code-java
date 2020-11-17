package videostore.horror;

import java.util.*;

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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = generateHeader();
		for (Rental rental : rentalList) {
			frequentRenterPoints += rental.computeEarnedFrequentPoints();
			result += generateBodyLine(rental, rental.computePrice());
			totalPrice += rental.computePrice();
			// pure = no side effects + same results
			// you are safe repeating a method call for pure & fast functions
		}
		result += generateFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String generateBodyLine(Rental rental, double price) {
		return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
	}


	private String generateHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String generateFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}


}