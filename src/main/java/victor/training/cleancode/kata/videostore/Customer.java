package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

    public void addRental(Movie m, int rentalDays) {
		rentals.put(m, rentalDays);
	}

	public String statement() {
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");
		// loop over each movie rental
		double totalPrice = 0;
		for (Map.Entry<Movie, Integer> rental : rentals.entrySet()) {
			Movie movie = rental.getKey();
			int daysRented = rental.getValue();
			double price = calculateNewPrice(movie, daysRented);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (shouldGetBonus(movie, daysRented)) {
				frequentRenterPoints++;
			}
			// show figures line for this rental
			result.append("\t").append(movie.title()).append("\t").append(price).append("\n");
			totalPrice += price;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalPrice).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static boolean shouldGetBonus(Movie movie, int daysRented) {
		Integer priceCode = movie.priceCode();
		return priceCode != null &&
				(priceCode == Movie.NEW_RELEASE)
				&& daysRented > 1;
	}

	private static double calculateNewPrice(Movie movie, int daysRented) {
		double price = 0;
		switch (movie.priceCode()) {
			case Movie.REGULAR -> price = calculateRegularMoviePrice(price, daysRented);
			case Movie.NEW_RELEASE -> price += daysRented * 3;
			case Movie.CHILDREN -> price = calculateChildrenMoviePrice(price, daysRented);
		}
		return price;
	}

	private static double calculateChildrenMoviePrice(double price, int daysRented) {
		price += 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}

	private static double calculateRegularMoviePrice(double price, int daysRented) {
		price += 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}
}