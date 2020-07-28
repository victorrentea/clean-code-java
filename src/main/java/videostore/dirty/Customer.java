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
		
		String result = "Rental Record for " + getName() + "\n";
		
		for (Rental rental:rentals) {
			
			// determine amounts for each line
			double currentPrice = determineAmount(rental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((rental.getMovie().getCategory() == Movie.Category.NEW_RELEASE)
					&& rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ currentPrice + "\n";
			totalPrice += currentPrice;
		}
		//pe master direct
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private double determineAmount(Rental each) {
		double thisAmount = 0;
		switch (each.getMovie().getCategory()) {
		case REGULAR:
			thisAmount += 2;
			if (each.getDaysRented() > 2)
				thisAmount += (each.getDaysRented() - 2) * 1.5;
			break;
		case NEW_RELEASE:
			thisAmount += each.getDaysRented() * 3;
			break;
		case CHILDRENS:
			thisAmount += 1.5;
			if (each.getDaysRented() > 3)
				thisAmount += (each.getDaysRented() - 3) * 1.5;
			break;
		}
		return thisAmount;
	}
}