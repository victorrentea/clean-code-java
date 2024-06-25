package victor.training.cleancode.kata.videostore;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<Rental> rentals = new LinkedList<>();  // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie m, int d) {
		rentals.add(new Rental(m, d));
	}
	public String statement() {
		return "Rental Record for " + name + "\n" +
				rentals.stream().map(rental -> "\t" + rental.movie().title() + "\t" + rental.calculatePrice()
						+ "\n").collect(Collectors.joining()) + "Amount owed is " +
				rentals.stream().mapToDouble(Rental::calculatePrice).sum()
				+ "\n" + "You earned " + rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum()
				+ " frequent renter points";
	}
}