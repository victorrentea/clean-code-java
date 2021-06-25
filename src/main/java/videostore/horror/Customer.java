package videostore.horror;

import java.util.*;

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

	public String buildStatement() {
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			frequentRenterPoints += rental.determineFrequentRenterPoints();
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + rental.determineAmount() + "\n";
		}
		double totalAmount = computeTotalAmount();

		result += formatFooter(totalAmount, frequentRenterPoints);
		return result;
	}

	private double computeTotalAmount() {
		return rentals.stream().mapToDouble(Rental::determineAmount).sum();
	}

	private String formatFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + "\n" +
				 "You earned " + frequentRenterPoints + " frequent renter points";
	}

}