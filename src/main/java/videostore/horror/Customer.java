package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

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
		return Collections.unmodifiableList(rentals);
	}
}