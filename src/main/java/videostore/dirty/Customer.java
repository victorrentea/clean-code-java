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

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = formatHeader();
		for (Rental rental:rentals) {
			frequentRenterPoints += rental.computeRenterPoints();
			result += formatItem(rental);
			totalPrice += rental.computeAmount();
		}
		result += formatFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n"
				+ "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private String formatItem(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computeAmount() + "\n";
	}
}