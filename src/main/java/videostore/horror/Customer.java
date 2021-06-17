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

	public String generateStatement() {
		int frequentRenterPoints = 0;
		String statement = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			boolean isNewRelease = movie.getPriceCode() == Movie.NEW_RELEASE;
			if (isNewRelease && daysRented >= 2) {
				frequentRenterPoints++;
			}

			// show figures line for this rental
			statement += "\t" + movie.getTitle() + "\t" + rental.computePrice() + "\n";
		}

		double totalPrice = computeTotalPrice();


		statement += generateFooter(totalPrice, frequentRenterPoints);
		return statement;
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private String generateFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}

}