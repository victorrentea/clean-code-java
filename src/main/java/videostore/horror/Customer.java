package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentalList = new ArrayList<>();


	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalList.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentalList) {
			frequentRenterPoints += rental.computeRenterPoints();
		}
		for (Rental rental : rentalList) {
			double price = rental.process();
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
		}
		for (Rental rental : rentalList) {
			double price = rental.process();
			totalPrice += price;
		}
		// what can go wrong if you call a f() twice ?\
		// 1) Performance (heavy computations) x 2 < < never a problem because pure functions
		// 2) random inputs inside >> the function could return different results > NOT REFERENTIAL TRANSPARENT
		// 3) side effects eg INSERT >> calling the f twice >> BAD DATA INSERTED

		// a function that does NOT do side effects and returns the same results when called with the same args = PURE FUNCTION

		result += formatFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String formatFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
			   + "You earned " + frequentRenterPoints + " frequent renter points";
	}

}