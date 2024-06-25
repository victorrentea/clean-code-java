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
		StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");
		double totalPrice = rentals.stream().mapToDouble(Rental::calcNewPrice).sum();
		rentals.forEach(rental -> {
			double price = rental.calcNewPrice();
			result.append("\t").append(rental.movie.title()).append("\t").append(price).append("\n");
		});
		// TODO use .collect(joining());
		int frequentRenterPoints = rentals.stream().mapToInt(Rental::calcFrequentRenterPoints).sum();
		// add footer lines
		result.append("Amount owed is ").append(totalPrice).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
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
		private double calcNewPrice() {
			int daysRented = this.daysRented;
			return switch (movie.priceCode()) {
				case REGULAR -> calcRegularMoviePrice(daysRented);
				case NEW_RELEASE -> calcNewReleaseMoviePrice(daysRented);
				case CHILDREN -> calcChildrenMoviePrice(daysRented);
			};
		}

		private boolean shouldGetBonus() {
			PriceCode priceCode = movie.priceCode();
			return priceCode == PriceCode.NEW_RELEASE
					&& daysRented > 1;
		}

		private int calcFrequentRenterPoints() {
			int frequentRenterPoints = 1;
			// add bonus for a two day new release rental
			if (shouldGetBonus()) {
				frequentRenterPoints++;
			}
			return frequentRenterPoints;
		}
	}
}