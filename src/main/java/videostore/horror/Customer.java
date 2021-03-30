package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
//	private final String phone;
//	private final String address;
//	private final String shoeSize;
	private final List<Rental> rentals = new ArrayList<>();

	public List<Rental> getRentals() {
		return rentals;
	}

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

}
