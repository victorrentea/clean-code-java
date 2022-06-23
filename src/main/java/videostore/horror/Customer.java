package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
	private final String name;
	private final List<Rental> rentalList = new ArrayList<>();


	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalList.add(new Rental(movie, daysRented));
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
		return rentalList.stream().map(this::formatLine).collect(joining());
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

	private String formatFooter() {
		double totalPrice = rentalList.stream().mapToDouble(Rental::computePrice).sum();
		int totalPoints = rentalList.stream().mapToInt(Rental::computeRenterPoints).sum();
		return "Amount owed is " + totalPrice + "\n"
			   + "You earned " + totalPoints + " frequent renter points";
	}

}