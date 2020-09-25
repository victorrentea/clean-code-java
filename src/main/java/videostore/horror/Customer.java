package videostore.horror;

import java.util.*;

class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}
}
class Customer {
	private String name;
	private Map<Movie, Integer> rentalMap = new LinkedHashMap<>(); // preserves order
	private final List<Rental> rentals =new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalMap.put(movie, daysRented);
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			double thisAmount = determineCurrentAmountForCurrentMovie(rental);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.getMovie().getCategory() != null &&
				 (rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
				 && rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
						 + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double determineCurrentAmountForCurrentMovie(Rental rental) {
		int dr = rental.getDaysRented();
		double thisAmount = 0;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += dr * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
				break;
		}
		return thisAmount;
	}

}