package videostore.dirty;
import videostore.dirty.Movie.Category;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}
	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String generateStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0; // TODO =.size()
		String result = formatHeader();
		for (Rental rental : rentals) {
			frequentRenterPoints += determineFrequentRenterPoints(rental);
			result += formatRentalLine(rental);
			totalPrice += determinePrice(rental);
		}
		result += formatFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private String formatRentalLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + determinePrice(rental) + "\n";
	}

	private int determineFrequentRenterPoints(Rental rental) {
		int frequentRenterPoints = 1;
		boolean isNewRelease = rental.getMovie().getCategory() == Category.NEW_RELEASE;
		if (isNewRelease && rental.getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	private double determinePrice(Rental rental) {
		double price = 0;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				price += 2;
				if (rental.getDaysRented() > 2) {
					price += (rental.getDaysRented() - 2) * 1.5;
				}
				break;
			case NEW_RELEASE:
				price += rental.getDaysRented() * 3;
				break;
			case CHILDRENS:
				price += 1.5;
				if (rental.getDaysRented() > 3) {
					price += (rental.getDaysRented() - 3) * 1.5;
				}
				break;
		}
		return price;
	}

}