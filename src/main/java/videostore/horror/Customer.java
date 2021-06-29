package videostore.horror;

import java.util.*;

import static videostore.horror.Movie.Category.NEW_RELEASE;

class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public double computePrice() {
		double result = 0.0;
//		someCounter ++; // side effect
		switch (getMovie().getCategory()) {
			case REGULAR:
				result += 2;
				if (getDaysRented() > 2)
					result += (getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				result += getDaysRented() * 3;
				break;
			case CHILDREN:
				result += 1.5;
				if (getDaysRented() > 3)
					result += (getDaysRented() - 3) * 1.5;
				break;
		}
		return result;
	}

	public int computeFrequentPoints() {
		int frequentRenterPoints = 1;
		boolean isNewRelease = getMovie().getCategory() == NEW_RELEASE;
		if (isNewRelease && getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
}

class Customer { // model
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() { // presentation
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();

			// computation
			frequentRenterPoints += rental.computeFrequentPoints(); // computation
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + rental.computePrice() + "\n"; // Presentation
			// changes to rental here.
			totalPrice += rental.computePrice(); // different result
			// a function that given the same args => same results. = Referential Transparent.
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}


}