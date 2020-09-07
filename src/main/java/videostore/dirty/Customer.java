package videostore.dirty;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Rental> getRentals() {
		return Collections.unmodifiableList(rentals);
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}
}

