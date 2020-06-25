package videostore.horror;

import java.util.*;
//class Days {
//	private final int days;
//}
class MovieWithDays {
	private final Movie movie;
	private final int daysRented;

	MovieWithDays(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
}
class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		rentals.put(movie, days);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

//		for (Movie movie : rentals.keySet()) {
//			int daysRented = rentals.get(movie);

		for (Map.Entry<Movie, Integer> entry : rentals.entrySet()) {
			Movie movie = entry.getKey();
			int daysRented = entry.getValue();


			double thisAmount = 0;
			// determine amounts for each line
			switch (movie.getPriceCode()) {
				case REGULAR:
					thisAmount += 2;
					if (daysRented > 2) {
						thisAmount += (daysRented - 2) * 1.5;
					}
					break;
				case NEW_RELEASE:
					thisAmount += daysRented * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (daysRented > 3) {
						thisAmount += (daysRented - 3) * 1.5;
					}
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getPriceCode() != null &&
				(movie.getPriceCode() == PriceCode.NEW_RELEASE)
				&& daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t"
				+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}
}