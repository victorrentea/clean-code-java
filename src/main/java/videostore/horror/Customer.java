package videostore.horror;

import java.util.*;
//class Days {
//	private final int days;
//}
class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}
}
class Customer {
	private final String name;
	private final Map<Movie, Integer> movieToDaysRented = new LinkedHashMap<>(); // preserves order
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		movieToDaysRented.put(movie, days);
		rentals.add(new Rental(movie, days));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

//		for (Movie movie : rentals.keySet()) {
//			int daysRented = rentals.get(movie);

//		for (Map.Entry<Movie, Integer> entry : movieToDaysRented.entrySet()) {
//			Movie movie = entry.getKey();
//			int daysRented = entry.getValue();

		for (Rental rental : rentals) {
			double thisAmount = 0;
			// determine amounts for each line
			switch (rental.getMovie().getPriceCode()) {
				case REGULAR:
					thisAmount += 2;
					if (rental.getDaysRented() > 2) {
						thisAmount += (rental.getDaysRented() - 2) * 1.5;
					}
					break;
				case NEW_RELEASE:
					thisAmount += rental.getDaysRented() * 3;
					break;
				case CHILDRENS:
					thisAmount += 1.5;
					if (rental.getDaysRented() > 3) {
						thisAmount += (rental.getDaysRented() - 3) * 1.5;
					}
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.getMovie().getPriceCode() != null &&
				(rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE)
				&& rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t"
				+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}
}