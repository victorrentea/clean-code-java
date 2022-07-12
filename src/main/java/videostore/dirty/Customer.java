package videostore.dirty;
import java.util.*;

import static java.lang.System.lineSeparator;

class Customer {
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

	public String generateStatement() {
		double totalAmount = 0;
		String result = generateHeader();

		int totalPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();

		for (Rental rental : rentals) {
			double thisAmount = rental.calculateAmount();
			totalAmount += thisAmount;

		}
		for (Rental rental : rentals) {
			double thisAmount = rental.calculateAmount();
			// ce poate sa mearga rau daca repeti un apel de functie?
			// 1 sa-ti dea rezultat diferit cand o chemi a doua oara
			// 2 daca functia chemata face SIDE EFFECTS (eg un INSERT)
			// * sa dureze timp (calcule multe) - query in DB, REST, SOAP,
// in pracitca pt ca 1+2 ==> *

			// definitie o functie PURE nu face nici (1) nici (2): adica iti da acelasi rezultat si nu face side effects
			result += generateBodyLine(rental, thisAmount);
		}
		result += generateFooter(totalAmount, totalPoints);
		return result;
	}

	private String generateBodyLine(Rental rental, double thisAmount) {
		return "\t" + rental.getMovie().getTitle() + "\t"
			   + thisAmount + lineSeparator();
	}

	private String generateHeader() {
		return "Rental Record for " + getName() + lineSeparator();
	}

	private String generateFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + lineSeparator()
			   + "You earned " + frequentRenterPoints + " frequent renter points";
	}

}