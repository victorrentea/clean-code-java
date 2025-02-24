package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
	@Getter
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie m, int days) {
		rentals.put(m, days);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder( "Rental Record for " + getName() + "\n" );
		// loop over each movie rental
		for (Movie movie : rentals.keySet()) {
			int dr = rentals.get(movie);
			double thisAmount = movie.getRentCost(dr);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two-day new release rental
			if ( movie.priceCode() == Movie.NEW_RELEASE && dr > 1 )
				frequentRenterPoints++;

			// show figures line for this rental
			result.append( "\t" ).append( movie.title() ).append( "\t" ).append( thisAmount ).append( "\n" );
			totalAmount += thisAmount;
		}
		// add footer lines
		result.append( "Amount owed is " ).append( totalAmount ).append( "\n" );
		result.append( "You earned " ).append( frequentRenterPoints ).append( " frequent renter points" );
		return result.toString();
	}
}