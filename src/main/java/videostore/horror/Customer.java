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

	public double computePrice() {
		double thisAmount = 0;
		switch (getMovie().getCategory()) {
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
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) { // TODO break the for
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			double price = rental.computePrice();

			frequentRenterPoints += computeRenterPoints(movie, daysRented);

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";

			totalAmount += price;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int computeRenterPoints(Movie movie, int daysRented) {
		int frequentRenterPoints = 1;
		boolean deservesBonus = movie.getCategory() == Category.NEW_RELEASE && daysRented >= 2 ;
		if (deservesBonus) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

}