package videostore.horror;

import videostore.horror.Movie.Category;

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

	public String statement() {
		double totalPrice = 0;
		int totalFrequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			double price = computePrice(rental);

			totalFrequentRenterPoints += computeFrequentRenterPoints(rental);
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + totalFrequentRenterPoints + " frequent renter points";
		return result;
	}

	private int computeFrequentRenterPoints(Rental rental) {
		int frequentRenterPoints = 1;
		boolean isNewRelease = rental.getMovie().getCategory() == Category.NEW_RELEASE;
		if (isNewRelease && rental.getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	private double computePrice(Rental rental) {
		double price = 0;

		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				price += 2;
				if (rental.getDaysRented() > 2)
					price += (rental.getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += rental.getDaysRented() * 3;
				break;
			case CHILDREN:
				price += 1.5;
				if (rental.getDaysRented() > 3)
					price += (rental.getDaysRented() - 3) * 1.5;
				break;
		}
		return price;
	}
}