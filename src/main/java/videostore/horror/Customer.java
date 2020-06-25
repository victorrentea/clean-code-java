package videostore.horror;

import java.util.*;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = createHeader();

		for (Rental rental : rentals) {
			double price = computePrice(rental);
			frequentRenterPoints += computeRenterPoints(rental);
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		result += createFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n" +
			"You earned " + frequentRenterPoints + " frequent renter points";
	}

	private int computeRenterPoints(Rental rental) {
		int points = 1;
		boolean isNewRelease = rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE;
		if (isNewRelease && rental.getDaysRented() >= 2) {
			points++;
		}
		return points;
	}

	private double computePrice(Rental rental) {
		double price = 0;
		switch (rental.getMovie().getPriceCode()) {
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