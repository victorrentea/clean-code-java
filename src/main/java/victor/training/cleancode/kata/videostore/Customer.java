package victor.training.cleancode.kata.videostore;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
class Customer {
	private final String name;
	private final List<Rental> rentalDays = new ArrayList<>(); // preserves order of elements TODO find a better way to store this
//	private final Map<Movie, Integer> rentalDays = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	}

	public void addRental( Movie movie, int days) {
		rentalDays.put( movie, days);
	}

	public String statement() {
		double totalDue = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		for (Movie movie : rentalDays.keySet()) {
			double balanceDue = 0;
			// determine amounts for every line
			int daysRented = rentalDays.get( movie);
			switch (movie.getCategory()) {
				case REGULAR:
					balanceDue += 2;
					if ( daysRented > 2)
						balanceDue += ( daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					balanceDue += daysRented * 3;
					break;
				case CHILDREN:
					balanceDue += 1.5;
					if ( daysRented > 3)
						balanceDue += ( daysRented - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (movie.getCategory() != null &&
				 (movie.getCategory() == Category.NEW_RELEASE )
				 && daysRented > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + balanceDue + "\n";
			totalDue += balanceDue;
		}
		// add footer lines
		result += "Amount owed is " + totalDue + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}
