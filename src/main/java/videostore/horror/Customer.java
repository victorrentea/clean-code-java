package videostore.horror;


import java.util.ArrayList;
import java.util.List;

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
		String result = "Rental Record for " + this.name + "\n";
		double totalAmount = 0;
		for (Rental rental : rentals) {
			// determine amounts for every line
			double thisAmount = rental.calculateAmount();
			// show figures line for this rental
			totalAmount += thisAmount;

			result += "\t" + rental.getMovie().title() + "\t" + thisAmount + "\n";
		}

		int frequentRenterPoints = rentals.stream()
				.mapToInt(Rental::calculateFrequentRenterPoints)
				.sum();
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

}