package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Customer {
	private String name;
	// preserves order
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public String getName() {
		return name;
	}


}

