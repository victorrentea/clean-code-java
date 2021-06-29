package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;
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
		return generateHeader()
				 + generateBody()
				 + generateFooter();
	}

	private String generateBody() {
		return rentals.stream().map(this::formatRental).collect(joining());
	}

	private String formatRental(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String generateHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String generateFooter() {
		int totalPoints = rentals.stream().mapToInt(Rental::computeFrequentPoints).sum();
		double totalPrice = rentals.stream().mapToDouble(Rental::computePrice).sum();

		String result = "Amount owed is " + totalPrice + "\n";
		result += "You earned " + totalPoints + " frequent renter points";
		return result;
	}


}