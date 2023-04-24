package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
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

	public String getReceipt() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Rental rental : rentalList) {

			Movie movie = rental.movie();
			int daysRented = rental.daysRented();
			double price = movie.category().computePrice(daysRented);

			frequentRenterPoints += computeBonusPoints(daysRented, movie.category());


			result += "\t" + movie.title() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static int computeBonusPoints(int daysRented, MovieCategory movieCategory) {
		int frequentRenterPoints = 1;

		if (movieCategory == MovieCategory.NEW_RELEASE && daysRented >= 2)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

}
