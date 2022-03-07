package videostore.dirty;
import java.util.*;

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
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";

		for (Rental each : rentals) {
			double thisAmount = 0;
			// determine amounts for each line

//			thisAmount = switch (each.getMovie().getCategory()) {
//				case REGULAR -> 1;
//				case NEW_RELEASE -> 2;
//				case CHILDREN,					ELDERS -> 3;
//			};

			switch (each.getMovie().getCategory()) {
				case REGULAR:
					thisAmount += 2;
					if (each.getDaysRented() > 2)
						thisAmount += (each.getDaysRented() - 2) * 1.5;
					break;
				case NEW_RELEASE:
					thisAmount += each.getDaysRented() * 3;
					break;
				case CHILDREN:
					thisAmount += 1.5;
					if (each.getDaysRented() > 3)
						thisAmount += (each.getDaysRented() - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
				 && each.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
						 + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}