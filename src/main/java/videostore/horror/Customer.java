package videostore.horror;

import java.util.*;
import java.util.Map.Entry;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;

	}



}

class Customer {
	private final String name;
	private final Map<Movie,Integer> rentals = new HashMap<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Entry<Movie, Integer> entry : rentals.entrySet()) {

			Movie movie = entry.getKey();
			int daysRented = entry.getValue();

			double price = computePrice(daysRented, movie.getCategory());
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((movie.getCategory() == MovieCategory.NEW_RELEASE) && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computePrice(int daysRented, MovieCategory category) {
		double price = 0;
		switch (category) {
		case REGULAR:
			price += 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			break;
		case NEW_RELEASE:
			price += daysRented * 3;
			break;
		case CHILDREN:
			price += 1.5;
			if (daysRented > 3)
				price += (daysRented - 3) * 1.5;
			break;
		}
		return price;
	}
}