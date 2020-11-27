package videostore.dirty;
import videostore.dirty.Movie.Category;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Customer {
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
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private String formatBody() {
		 return rentals.stream().map(this::composeRentalTitleAndPrice).collect(joining());
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		String result = "Amount owed is " + determineTotalPrice() + "\n";
		result += "You earned " + determineTotalPoints() + " frequent renter points";
		return result;
	}

	private int determineTotalPoints() {
		return rentals.stream().mapToInt(this::determineFrequentRenterPoints).sum();
	}

	private double determineTotalPrice() {
		return rentals.stream().mapToDouble(this::determinePrice).sum();
	}


	private String composeRentalTitleAndPrice(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + determinePrice(rental) + "\n";
	}


	private int determineFrequentRenterPoints(Rental rental) {
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if ((rental.getMovie().getCategory() == Category.NEW_RELEASE)
			 && rental.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private double determinePrice(Rental rental) {
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
		case CHILDRENS:
			price += 1.5;
			if (rental.getDaysRented() > 3)
				price += (rental.getDaysRented() - 3) * 1.5;
			break;
		}
		return price;
	}
}