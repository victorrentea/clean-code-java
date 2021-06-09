package videostore.horror;

import java.util.*;

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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = formatHeader();
		for (Rental rental : rentals) {
			frequentRenterPoints += rental.calculateBonus();
			result += formatBodyLine(rental);
			totalPrice += rental.calculatePrice();
		}
		result += formatFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}


}