package videostore.dirty;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
// ce poate sa mearga rau daca repeti un apel de functie?
// 1 sa-ti dea rezultat diferit cand o chemi a doua oara
// 2 daca functia chemata face SIDE EFFECTS (eg un INSERT)
// * sa dureze timp (calcule multe) - query in DB, REST, SOAP,
// in pracitca pt ca 1+2 ==> *

// definitie o functie PURE nu face nici (1) nici (2): adica iti da acelasi rezultat si nu face side effects
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
		String result = generateHeader();

		int totalPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();

		double totalAmount = rentals.stream().mapToDouble(Rental::calculateAmount).sum();

		result += rentals.stream().map(this::generateBodyLine).collect(joining());

		result += generateFooter(totalAmount, totalPoints);
		return result;
	}

	private String generateBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t"
			   + rental.calculateAmount() + lineSeparator();
	}

	private String generateHeader() {
		return "Rental Record for " + getName() + lineSeparator();
	}

	private String generateFooter(double totalAmount, int frequentRenterPoints) {
		return "Amount owed is " + totalAmount + lineSeparator()
			   + "You earned " + frequentRenterPoints + " frequent renter points";
	}

}