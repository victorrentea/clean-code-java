package videostore.horror;

import java.util.*;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentals) {
			double thisAmount = computeAmount(rental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.getMovie().getPriceCode() != null &&
				(rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE)
				&& rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
				+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double computeAmount(Rental rental) {
		double thisAmount = 0;
		// determine amounts for each line
		switch (rental.getMovie().getPriceCode()) {
			case REGULAR:
				thisAmount += 2;
				if (rental.getDaysRented() > 2) {
					thisAmount += (rental.getDaysRented() - 2) * 1.5;
				}
				break;
			case NEW_RELEASE:
				thisAmount += rental.getDaysRented() * 3;
				break;
			case CHILDRENS:
				thisAmount += 1.5;
				if (rental.getDaysRented() > 3) {
					thisAmount += (rental.getDaysRented() - 3) * 1.5;
				}
				break;
		}
		return thisAmount;
	}
}