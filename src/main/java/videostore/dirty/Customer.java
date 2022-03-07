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
		String result = "Rental Record for " + name + "\n";


		int frequentRenterPoints = rentals.stream().mapToInt(Rental::computeRenterPoints).sum();

		for (Rental rental : rentals) {

			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + computeAmount(rental) + "\n";
			totalAmount += computeAmount(rental);
			// repeating a function call with same args can:
			// 1)BUG  > if the method does SIDE-EFFECTS eg. INSERT, incrementX
			// 2)BUG  > if the function for the same args returns DIFFERENT RESULTS
			// 3) take time (fct computes a lot of stuff)

			// 1 + 2: no side effects and same out for same params = PURE FUNCTION

			// --
			// in any case if tomorrow someone changes the code without testing it.. BUM
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
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