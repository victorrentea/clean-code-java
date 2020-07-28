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
			result += formatItem(rental, computeAmount(rental));
			totalPrice += computeAmount(rental);
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

	private String formatItem(Rental rental, double currentPrice) {
		return "\t" + rental.getMovie().getTitle() + "\t" + currentPrice + "\n";
	}

	private double computeAmount(Rental each) {
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