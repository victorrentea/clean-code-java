package videostore.dirty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

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
