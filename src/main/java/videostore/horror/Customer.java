package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.*;

class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
}
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
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			double price = computePrice(movie, daysRented);

			frequentRenterPoints++;
			boolean deservesBonus = movie.getCategory() == Category.NEW_RELEASE && daysRented  >= 2 ;
			if (deservesBonus) {
				frequentRenterPoints++;
			}

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";

			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computePrice(Movie each, int dr) {
		double thisAmount = 0;
		switch (each.getCategory()) {
			case REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}