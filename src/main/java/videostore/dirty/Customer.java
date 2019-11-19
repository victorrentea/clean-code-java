package videostore.dirty;
import java.util.*;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";
		for (Rental rental : rentals) {
			double price = computePrice(rental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
					&& rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints
				+ " frequent renter points";
		return result;
	}

	private double computePrice(Rental rental) {
		double price = 0;
		switch (rental.getMovie().getCategory()) {
			case REGULAR:
				price += 2;
				if (rental.getDaysRented() > 2)
					price += (rental.getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += rental.getDaysRented() * 3;
				break;
			case CHILDREN:
				price += 1.5;
				if (rental.getDaysRented() > 3)
					price += (rental.getDaysRented() - 3) * 1.5;
				break;
		}
		return price;
	}
}