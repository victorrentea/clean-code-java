package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
		StringBuilder result = new StringBuilder( "Rental Record for " + getName() + "\n" );
		double totalAmount = rentals.stream().mapToDouble( MovieRental::getPrice ).sum();
		int frequentRenterPoints = rentals.stream().mapToInt( MovieRental::getFrequentRenterPoints ).sum();
		result.append( rentals.stream().map(MovieRental::getStatement).collect( Collectors.joining() ) );
		// add footer lines
		result.append( "Amount owed is " ).append( totalAmount ).append( "\n" );
		result.append( "You earned " ).append( frequentRenterPoints ).append( " frequent renter points" );
		return result.toString();
	}
}