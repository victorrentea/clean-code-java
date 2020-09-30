package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;


// ADvanced: enum cu Function<> ---
// Alexandr: Nu e ok sa calculezi treburi cand si formateze >>

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

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