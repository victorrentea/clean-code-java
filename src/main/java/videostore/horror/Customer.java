package videostore.horror;

import static java.util.stream.Collectors.joining;

import java.util.*;
import java.util.Map.Entry;

import videostore.horror.Movie.Type;


class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	private boolean addRental(Rental rental) {
		return rentals.add(rental);
	}

	public String getName() {
		return name;
	}
	public List<Rental> getRentals() {
		return rentals;
	}
	
	
	static {
		Customer c = new Customer("h");
		c.addRental(new Rental(new Movie("title", Type.REGULAR), 3));
	}
	
}