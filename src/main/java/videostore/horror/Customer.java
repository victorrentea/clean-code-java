package videostore.horror;

import java.util.*;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
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

	public String createStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		// header
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentalList) {
			double price = determinePrice(rental.getMovie(), rental.getDaysRented());
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE && rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double determinePrice(Movie movie, int daysRented) {
		double price = 0;
		switch (movie.getCategory()) {
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
		default:
			throw new IllegalStateException("Unexpected value: " + movie.getCategory());
		}
		return price;
	}
}