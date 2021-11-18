package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static videostore.horror.Movie.Type.NEW_RELEASE;


class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public Movie getMovie() {
		return movie;
	}

	public double computePrice() {
		return switch (getMovie().getPriceCode()) {
			case REGULAR -> computeRegularPrice();
			case NEW_RELEASE -> computeNewReleasePrice();
			case CHILDRENS -> computeChildrenPrice();
		};
	}

	private int computeNewReleasePrice() {
		return getDaysRented() * 3;
	}

	private double computeChildrenPrice() {
		double price = 1.5;
		if (getDaysRented() > 3)
			price += (getDaysRented() - 3) * 1.5;
		return price;
	}

	private double computeRegularPrice() {
		double price = 2;
		if (getDaysRented() > 2)
			price += (getDaysRented() - 2) * 1.5;
		return price;
	}

	public boolean deservesBonus() {
		return getMovie().getPriceCode() == NEW_RELEASE && getDaysRented() >= 2;
	}

	public int computeBonus() {
		int frequentRenterPoints = 1;
		if (deservesBonus()) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
}

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		return formatHeader()
				 + formatBody()
				 + formatFooter();
	}

	private double getTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int getTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeBonus).sum();
	}

	private String formatBody() {
		return rentals.stream().map(this::formatLine).collect(Collectors.joining());
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + getTotalPrice() + "\n" +
				 "You earned " + getTotalPoints() + " frequent renter points";
	}

	private String formatLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}

}