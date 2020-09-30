package videostore.horror;

import videostore.horror.Movie.Category;

import java.util.*;
import java.util.stream.Collectors;


// ADvanced: enum cu Function<> ---
// Alexandr: Nu e ok sa calculezi treburi cand si formateze >>

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
		return formatHeader() +
				 formatBody() +
				 formatFooter();
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatBody() {
		return rentals.stream().map(this::formatBodyLine).collect(Collectors.joining());
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(this::computePrice).sum();
	}

	private int computeTotalFrequentRenterPoints() {
		return rentals.stream().mapToInt(this::computeFrequentRenterPoints).sum();
	}

	private String formatBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + computePrice(rental) + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n" +
				 "You earned " + computeTotalFrequentRenterPoints() + " frequent renter points";
	}

	private int computeFrequentRenterPoints(Rental rental) {
		int frequentRenterPoints = 1;
		boolean isNewRelease = rental.getMovie().getCategory() == Category.NEW_RELEASE;
		if (isNewRelease && rental.getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
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