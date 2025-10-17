package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
	private final String name;
	private final Map<Movie, Integer> movieRentalDuration = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		movieRentalDuration.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
        int rewardPoints = 0;
		StringBuilder statement = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
        double totalCost = 0;
        for (Movie movie : movieRentalDuration.keySet()) {
            // determine amounts for every line
            int daysRented = movieRentalDuration.get(movie);
            double movieCost = getMovieCost(movie, daysRented);
            // add frequent renter points
			rewardPoints++;
			// add bonus for a two day new release rental
			if (movie.getPriceCode() == Movie.PriceCode.NEW_RELEASE && daysRented > 1)
				rewardPoints++;
			// show figures line for this rental
			statement.append("\t").append(movie.getTitle()).append("\t").append(movieCost).append("\n");
			totalCost += movieCost;
		}
		// add footer lines
		statement.append("Amount owed is ").append(totalCost).append("\n");
		statement.append("You earned ").append(rewardPoints).append(" frequent renter points");
		return statement.toString();
	}

    private static double getMovieCost(Movie movie, int daysRented) {
        double movieCost = 0;
        switch (movie.getPriceCode()) {
            case REGULAR:
                movieCost += 2;
                if (daysRented > 2)
                    movieCost += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                movieCost += daysRented * 3;
                break;
            case CHILDRENS:
                movieCost += 1.5;
                if (daysRented > 3)
                    movieCost += (daysRented - 3) * 1.5;
                break;
        }
        return movieCost;
    }
}