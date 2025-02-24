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

			double balanceDue = getBalanceDue(rental);
			// show figures line for this rental
			result += "\t" + rental.movie().getTitle() + "\t" + balanceDue + "\n";
			totalDue += balanceDue;
		}

		int frequentRenterPoints = getFrequentRenterPoints( rentals );
		// add footer lines
		result += "Amount owed is " + totalDue + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double getBalanceDue( Rental rental )
	{
		double balanceDue = 0;
		int daysRented = rental.rentalDays();
		Movie movie = rental.movie();
		switch ( movie.getCategory()) {
			case REGULAR:
				balanceDue += 2;
				if ( daysRented > 2)
					balanceDue += ( daysRented - 2) * 1.5;
        return balanceDue;
			case NEW_RELEASE:
				balanceDue += daysRented * 3;
        return balanceDue;
			case CHILDREN:
				balanceDue += 1.5;
				if ( daysRented > 3)
					balanceDue += ( daysRented - 3) * 1.5;
        return balanceDue;
		}
		return balanceDue;
	}

	private int getFrequentRenterPoints( List<Rental> rentals )
	{
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			Movie movie = rental.movie();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ( movie.getCategory() == Category.NEW_RELEASE && rental.rentalDays() > 1 )
				frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
}
