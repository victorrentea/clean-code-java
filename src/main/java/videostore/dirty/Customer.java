package videostore.dirty;
import java.util.*;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental arg) {
		rentals.add(arg);
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public String getName() {
		return name;
	}
}