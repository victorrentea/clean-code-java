package videostore.dirty;
import java.util.*;

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
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			double thisAmount = 0;

			// determine amounts for each line
			switch (rental.getMovie().getCategory()) {
				case REGULAR:
					thisAmount += 2;
					if (rental.getDaysRented() > 2)
						thisAmount += (rental.getDaysRented() - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += rental.getDaysRented() * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (rental.getDaysRented() > 3)
						thisAmount += (rental.getDaysRented() - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
				 && rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
						 + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		//pe master direct
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}