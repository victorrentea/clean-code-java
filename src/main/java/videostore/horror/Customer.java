package videostore.horror;

import java.util.*;
import java.util.Map.Entry;

import static videostore.horror.Movie.Category.NEW_RELEASE;

class Customer {

	private final String name;
	private final Map<Movie, Integer> rentalMap = new LinkedHashMap<>(); // preserves order
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentalMap.put(movie, daysRented);
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int totalPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

//		for (Movie movie : rentals.keySet()) { // THIS LINE HAS A SUBTLE PROBLEM
//			int daysRented = rentals.get(movie);

//		for (Entry<Movie, Integer> entry : rentalMap.entrySet()) { // THIS LINE HAS A SUBTLE DESIGN PROBLEM

		for (Rental rental : rentals) {
			int daysRented = rental.getDaysRented();
			Movie movie = rental.getMovie();


			double price = computePrice(movie, daysRented);

			totalPoints += calculateRenterPoints(movie, daysRented);
			// show figures line for this rental
			result.append("\t").append(movie.getTitle()).append("\t").append(price).append("\n");
			totalPrice += price;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalPrice).append("\n");
		result.append("You earned ").append(totalPoints).append(" frequent renter points");
		return result.toString();
	}

	private int calculateRenterPoints(Movie movie, int daysRented) {
		int result = 1;
		boolean isNewRelease = movie.getCategory() == NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			result ++;
		}
		return result;
	}

	private double computePrice(Movie movie, int daysRented) {
		double price = 0;
		switch (movie.getCategory()) {
			case REGULAR:
				price += 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += daysRented * 3;
				break;
			case CHILDREN:
				price += 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				break;
		}
		return price;
	}
}