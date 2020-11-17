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

			double price = rental.computePrice();
			frequentRenterPoints += rental.computeEarnedFrequentPoints();
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		result += generateFooter(totalPrice, frequentRenterPoints);
		return result;
	}




	private String generateHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String generateFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}


}