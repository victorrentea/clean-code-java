package videostore.dirty;

import java.util.List;

// @Service @Stateless
class StatementMaker{
	public String makeStatement(List<Rental> rentals, String name) {
		String result = makeHeader(name);
		for (Rental rental : rentals) {
			result += makeFigures(rental);
		}
		result += makeFooter(calculateTotalPrice(rentals), calculateTotalPoints(rentals));
		return result;
	}

	private double calculateTotalPrice(List<Rental> rentals) {
		double totalPrice = 0;
		for (Rental rental : rentals) {
			totalPrice += rental.calculatePrice();
		}
		return totalPrice;
	}

	private int calculateTotalPoints(List<Rental> rentals) {
		int frequentRenterPoints = 0;
		for (Rental rental : rentals) {
			frequentRenterPoints += rental.addPoints();
		}
		return frequentRenterPoints;
	}
	
	private String makeHeader(String name) {
		return "Rental Record for " + name + "\n";
	}

	private String makeFooter(double totalPrice, int frequentRenterPoints) {
		String result = "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private String makeFigures(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
	}
}