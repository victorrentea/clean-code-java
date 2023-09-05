package videostore.horror;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {

	private final String name;

	// TODO: Lista de Rental
	private final List<Rental> rentals = new ArrayList<>(); // preserves order


	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public String statement() {

		double totalAmount = rentals.stream().mapToDouble(Rental::calculateAmount).sum();

		int frequentRenterPoints = rentals.stream()
				.mapToInt(Rental::calculateFrequentRenterPoints)
				.sum();

		String result = "Rental Record for " + this.name + "\n";
		result += rentals.stream().map(this::formatRental).collect(Collectors.joining());
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private String formatRental(Rental rental) {
		return "\t" + rental.getMovie().title() + "\t" + rental.calculateAmount() + "\n";
	}

}