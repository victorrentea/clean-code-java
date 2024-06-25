package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
	@Getter
    private String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

    public String statement() {
		double totalAmount = 0;
        String result = "Rental Record for " + getName() + "\n";
		// loop over each movie rental
		Set<Movie> movies = rentals.keySet();
        int frequentRenterPoints = getFrequentRenterPoints(movies);

		for (Movie movie : movies) {
			// determine amounts for every line
			int daysRented = rentals.get(movie);
			double price = movie.priceCode().getPrice(daysRented);
			// show figures line for this rental
			result += "\t" + movie.title() + "\t" + price + "\n";
			totalAmount += price;
		}

		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int getFrequentRenterPoints(Set<Movie> movies) {
		return movies.stream()
				.mapToInt(movie -> (int) movie.priceCode().getFrequentRenterPoints(rentals.get(movie)))
				.sum();

	}

}