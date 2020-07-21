package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String createStatement() {
		double totalPrice = 0;
		int totalPoints = 0;
		String result = createHeader();

		for (Rental rental : rentals) {
			double price = rental.determinePrice();
			totalPoints += determineRenterPoints(rental);
			result += createLine(rental, price);
			totalPrice += price;
		}
		result += createFooter(totalPrice, totalPoints);
		return result;
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createFooter(double totalPrice, int totalPoints) {
		return "Amount owed is " + totalPrice + "\n" +
			"You earned " + totalPoints + " frequent renter points";
	}

	private String createLine(Rental rental, double price) {
		return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
	}

	private int determineRenterPoints(Rental rental) {
		int points = 1;
		if (rental.getMovie().isNewRelease() && rental.getDaysRented() >= 2) {
			points++;
		}
		return points;
	}

}