package victor.training.cleancode.kata.videostore;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental( Movie movie, int days) {
		rentals.add( new Rental( movie, days ) );
	}

	public String statement() {
    String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
    double totalDue = 0;
    for (Rental rental : rentals) {
			double balanceDue = 0;
			// determine amounts for every line
			int daysRented = rental.rentalDays();

			Movie movie = rental.movie();
			switch ( movie.getCategory()) {
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

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + balanceDue + "\n";
			totalDue += balanceDue;
		}

		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			Movie movie = rental.movie();
			int daysRented = rental.rentalDays();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ( movie.getCategory() == Category.NEW_RELEASE && daysRented > 1 )
				frequentRenterPoints++;
		}
		// add footer lines
		result += "Amount owed is " + totalDue + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}
