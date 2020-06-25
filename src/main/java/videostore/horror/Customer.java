package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public String getName() {
		return name;
	}






}