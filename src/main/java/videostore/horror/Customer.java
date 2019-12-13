package videostore.horror;

import java.util.*;


class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int d) {
		rentals.add(new Rental(movie, d));
	}

	public String getName() {
		return name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}
}