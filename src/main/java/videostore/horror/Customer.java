package videostore.horror;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static videostore.horror.MovieCategory.NEW_RELEASE;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String createStatement() {
		String result = createHeader();
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			// determine amounts for each line
			int daysRented = rental.getDaysRented();
			Movie movie = rental.getMovie();
			double thisAmount = computeAmount(daysRented, movie.getCategory());
			// add frequent renter points
			frequentRenterPoints += getFrequentRenterPoints(movie, daysRented);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		result += createFooter(totalAmount, frequentRenterPoints);
		return result;
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private static String createFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + "\n"
				+ "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private int getFrequentRenterPoints(Movie movie, int daysRented) {
		boolean isEligibleForBonus = movie.getCategory() == NEW_RELEASE && daysRented >= 2;
		return isEligibleForBonus ? 2 : 1;
	}

	private static double computeAmount(int daysRented, MovieCategory movieCategory) {
		double thisAmount = 0;
		switch (movieCategory) {
			case REGULAR:
				thisAmount += 2;
				if (daysRented > 2)
					thisAmount += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += daysRented * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (daysRented > 3)
					thisAmount += (daysRented - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}