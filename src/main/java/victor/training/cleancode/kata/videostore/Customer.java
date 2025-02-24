package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

class Customer {
	@Getter
	private final String name;
	private final List<MovieRental> rentals = new LinkedList<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int rentalDays) {
		rentals.add(new MovieRental(movie, rentalDays));
	}

	public String statement() {
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder( "Rental Record for " + getName() + "\n" );
		double totalAmount = 0; // rentals.stream().mapToDouble( MovieRental::getPrice ).sum();

		// loop over each movie rental
		for (MovieRental movieRental : rentals) {
			double thisAmount = 0;
			// determine amounts for every line
			thisAmount = movieRental.getPrice();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two-day new release rental
			if ( movieRental.movie().priceCode() == Movie.NEW_RELEASE && movieRental.rentalDays() > 1 )
				frequentRenterPoints++;
			// show figures line for this rental
			result.append( "\t" ).append( movieRental.movie().title() ).append( "\t" ).append( thisAmount ).append( "\n" );
			totalAmount += thisAmount;
		}
		// add footer lines
		result.append( "Amount owed is " ).append( totalAmount ).append( "\n" );
		result.append( "You earned " ).append( frequentRenterPoints ).append( " frequent renter points" );
		return result.toString();
	}
}