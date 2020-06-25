package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		rentals.add(new Rental(movie, days));
	}

	public String getName() {
		return name;
	}

	public String createStatement() {
		return createHeader()
			+ createBody()
			+ createFooter();
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createBody() {
		return rentals.stream().map(this::createStatementLine).collect(joining());
	}

	private String createFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
			"You earned " + computeTotalPoints() + " frequent renter points";
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(this::computeRenterPoints).sum();
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(this::computePrice).sum();
	}

	private String createStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + computePrice(rental) + "\n";
	}

	private int computeRenterPoints(Rental rental) {
		int points = 1;
		boolean isNewRelease = rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE;
		if (isNewRelease && rental.getDaysRented() >= 2) {
			points++;
		}
		return points;
	}

	private double computePrice(Rental rental) {
		double price = 0;
		switch (rental.getMovie().getPriceCode()) {
			case REGULAR:
				price += 2;
				if (rental.getDaysRented() > 2) {
					price += (rental.getDaysRented() - 2) * 1.5;
				}
				break;
			case NEW_RELEASE:
				price += rental.getDaysRented() * 3;
				break;
			case CHILDRENS:
				price += 1.5;
				if (rental.getDaysRented() > 3) {
					price += (rental.getDaysRented() - 3) * 1.5;
				}
				break;
		}
		return price;
	}
}