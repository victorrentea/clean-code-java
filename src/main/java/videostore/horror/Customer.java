package videostore.horror;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}

	public String getReceipt() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie movie : rentals.keySet()) {
			// determine amounts for movie line
			int daysRented = rentals.get(movie);
			double price = calculatePrice(movie, daysRented);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((movie.category() == MovieCategory.NEW_RELEASE)
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.title() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double calculatePrice(Movie movie, int daysRented) {
		double price;
		switch (movie.category()) {
			case REGULAR:
				price = 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price = daysRented * 3;
				break;
			case CHILDRENS:
				price = 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + movie.category());
		}
		return price;
	}
}