package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static videostore.horror.Movie.Category.NEW_RELEASE;

class Customer { // model
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}
}