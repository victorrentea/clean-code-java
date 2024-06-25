package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

    public void addRental(Movie m, int rentalDays) {
		rentals.add(new Rental(m, rentalDays));
	}

	public String statement() {
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");
		// loop over each movie rental
		double totalPrice = 0;
		for (Rental rental : rentals) {
			double price = calcNewPrice(rental);
			totalPrice += price;
			// show figures line for this rental
			result.append("\t").append(rental.movie.title()).append("\t").append(price).append("\n");
		}
		for (Rental rental : rentals) {
			// add frequent renter points
			frequentRenterPoints = calcFrequentRenterPoints(frequentRenterPoints, rental);

		}
		// add footer lines
		result.append("Amount owed is ").append(totalPrice).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

	private static int calcFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if (shouldGetBonus(rental.movie, rental.daysRented)) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

	private static boolean shouldGetBonus(Movie movie, int daysRented) {
		Integer priceCode = movie.priceCode();
		return priceCode != null &&
				(priceCode == Movie.NEW_RELEASE)
				&& daysRented > 1;
	}

	private static double calcNewPrice(Rental rental) {
		int daysRented = rental.daysRented;
		switch (rental.movie.priceCode()) {
			case Movie.REGULAR -> {
				return calcRegularMoviePrice(daysRented);
			}
			case Movie.NEW_RELEASE -> {
				return calcNewReleaseMoviePrice(daysRented);
			}
			case Movie.CHILDREN -> {
				return calcChildrenMoviePrice(daysRented);
			}
        }
		return 0;
	}

	private static int calcNewReleaseMoviePrice(int daysRented) {
		return daysRented * 3;
	}

	private static double calcChildrenMoviePrice(int daysRented) {
		double price = 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}

	private static double calcRegularMoviePrice(int daysRented) {
		double price = 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}

	record Rental(Movie movie, int daysRented) {
	}
}