package videostore.dirty;
import java.util.*;
import java.util.stream.Collectors;

public class Customer {
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

	public String statement() {
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {

		return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + computeAmount(rental) + "\n";
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		int frequentRenterPoints = rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
		double totalAmount = rentals.stream().mapToDouble(this::computeAmount).sum();
		String string = "Amount owed is " + totalAmount + "\n";
		return string
				 + "You earned " + frequentRenterPoints + " frequent renter points";
	}

	private double computeAmount(Rental rental) {
		double thisAmount = 0;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				thisAmount += 2;
				if (rental.getDaysRented() > 2)
					thisAmount += (rental.getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				thisAmount += rental.getDaysRented() * 3;
				break;
			case CHILDREN:
				thisAmount += 1.5;
				if (rental.getDaysRented() > 3)
					thisAmount += (rental.getDaysRented() - 3) * 1.5;
				break;
		}
		return thisAmount;
	}
}