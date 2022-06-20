package videostore.horror;

import java.util.*;


class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order (someone was sorry here, felt the need to drop a comment because the code was not explciti)


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

//		for (Movie movie : rentals.keySet()) {
//			int daysRented = rentals.get(movie);

		for (Map.Entry<Movie, Integer> entry : rentals.entrySet()) {
			Movie movie = entry.getKey();
			Integer daysRented = entry.getValue();

			double price = computePrice(movie, daysRented);
			// add frequent renter points
			frequentRenterPoints += computeRenterPoints(movie.getPriceCode(), daysRented);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int computeRenterPoints(Movie.PriceCode priceCode, int daysRented) {
		int frequentRenterPoints = 1;
		if (priceCode == Movie.PriceCode.NEW_RELEASE && daysRented >= 2)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private double computePrice(Movie movie, int daysRented) {
		switch (movie.getPriceCode()) {
			case REGULAR:
				return computeRegularPrice(daysRented);
			case NEW_RELEASE:
				return computeNewReleasePrice(daysRented);
			case CHILDREN:
				return computeChildrenPrice(daysRented);
			default:
				throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
		}
		// day-dream: Java 17
//		return switch (each.getPriceCode()) {
//			case REGULAR -> computeRegularPrice(daysRented);
//			case NEW_RELEASE -> computeNewReleasePrice(daysRented);
//			case CHILDREN -> computeChildrenPrice(daysRented);
//			default -> throw new IllegalStateException("Unexpected value: " + each.getPriceCode());
//		};
	}

	private double computeChildrenPrice(int daysRented) {
		double price =  1.5;
		if (daysRented > 3) {
			price += (daysRented - 3) * 1.5;
		}
		return price;
	}

	private int computeNewReleasePrice(int daysRented) {
		return daysRented * 3;
	}

	private double computeRegularPrice(int daysRented) {
		double price =  2;
		if (daysRented > 2) {
			price += (daysRented - 2) * 1.5;
		}
		return price;
	}
}