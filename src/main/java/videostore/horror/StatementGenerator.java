package videostore.horror;

import static java.util.stream.Collectors.joining;

import java.util.List;

import videostore.horror.Movie.Type;


//VIEW component
public class StatementGenerator {
//	private List<Rentals> rentals; + constru --> will make this class stateful,
	// not manageable by Spring
	

	public String createStatement(List<Rental> rentals, String customerName) {
		return createHeader(customerName) + createBody(rentals) + createFooter(rentals);
	}

	private String createBody(List<Rental> rentals) {
		return rentals.stream().map(this::createBodyLine).collect(joining());
	}

	private String createFooter(List<Rental> rentals) {
		return "Amount owed is " + calculateTotalPrice(rentals) + "\n" +
				"You earned " + calculateTotalRenterPoints(rentals) + " frequent renter points";
	}

	private double calculateTotalPrice(List<Rental> rentals) {
		return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
	}

	private int calculateTotalRenterPoints(List<Rental> rentals) {
		return rentals.stream().mapToInt(Rental::calculateRenterPoints).sum();
	}

	private String createBodyLine(Rental rental) {
		String extra = "";
		if (rental.getMovie().getType() == Type.CHILDRENS)  { extra = "butterfies"; }
		return "\t" + rental.getMovie().getTitle() + extra + "\t" + rental.calculatePrice() + "\n";
	}

	private String createHeader(String customerName) {
		return "Rental Record for " + customerName + "\n";
	}

}
