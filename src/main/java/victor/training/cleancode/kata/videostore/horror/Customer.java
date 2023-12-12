package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

import static victor.training.cleancode.kata.videostore.horror.MovieCategory.REGULAR;

class Customer {

	private final String name;

	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int noRentedDays) {
		rentals.put(movie, noRentedDays);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Movie each : rentals.keySet()) {
			// determine amounts for every line
			int noDaysRented = rentals.get(each);

			double thisAmount = calculateAmountOfCurrentMovie(each.getMovieCategory(),noDaysRented);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getMovieCategory() != null &&
				 (each.getMovieCategory() == MovieCategory.NEW_RELEASE)
				 && noDaysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double calculateAmountOfCurrentMovie(MovieCategory category,int noDaysRented){
		double thisAmount = 0;
		switch (category) {
			case REGULAR -> {
				thisAmount += 2;
				if (noDaysRented > 2)
					thisAmount += (noDaysRented - 2) * 1.5;
			}
			case NEW_RELEASE -> thisAmount += noDaysRented * 3;
			case CHILDREN -> {
				thisAmount += 1.5;
				if (noDaysRented > 3)
					thisAmount += (noDaysRented - 3) * 1.5;
			}
		}
		return thisAmount;
	}



	public Map<Movie, Integer> getRentals() {
		return rentals;
	}
}