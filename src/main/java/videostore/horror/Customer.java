package videostore.horror;

import java.util.*;

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
}

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>();
	private final Map<Movie, Integer> rentalsMap = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalsMap.put(movie, daysRented);
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totaPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();
			int daysRented = rental.getDaysRented();

			double price = computePrice(rental);

			// add frequent renter points
			frequentRenterPoints++;
			if (deservesBonus(movie, daysRented)) {
				frequentRenterPoints++;
			}

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
			totaPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totaPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double computePrice(Rental rental) {
		double price = 0;
		switch (rental.getMovie().getPriceCode()) {
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
			default: //always
				throw new IllegalStateException("Unexpected value: " + rental.getMovie().getPriceCode());
		}
		return price;
	}

	private boolean deservesBonus(Movie each, int daysRented) {
		return each.getPriceCode() == NEW_RELEASE && daysRented >= 2;
	}
}