package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		rentals.put(movie, days);
	}

	public String getName() {
		return name;
	}

	public String statement() {
        long frequentRenterPoints = rentals.size() + rentals.keySet().stream()
                                .filter(m -> isEligibleForBonus(m, rentals.get(m)))
                                .count();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
        double totalRentalFee = rentals.keySet().stream()
                .mapToDouble(movie -> getRentalFee(movie, rentals.get(movie))).sum();

        result.append(rentals.keySet().stream().map(this::getMovieAndPriceString).collect(Collectors.joining()));
		// add footer lines
		result.append("Amount owed is ").append(totalRentalFee).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

    private boolean isEligibleForBonus(Movie m, Integer daysRented) {
        return m.priceCode() == Movie.PriceCode.NEW_RELEASE && daysRented > 1;
    }

    private String getMovieAndPriceString(Movie movie) {
        return "\t" + movie.title() + "\t" +
                getRentalFee(movie, rentals.get(movie)) + "\n";
    }

    private static double getRentalFee(Movie movie, int daysRented) {
        double rentalFee = 0;
        switch (movie.priceCode()) {
            case REGULAR -> {
                rentalFee += 2;
                if (daysRented > 2)
                    rentalFee += (daysRented - 2) * 1.5;
            }
            case NEW_RELEASE -> rentalFee += daysRented * 3;
            case CHILDRENS -> {
                rentalFee += 1.5;
                if (daysRented > 3)
                    rentalFee += (daysRented - 3) * 1.5;
            }
        }
        return rentalFee;
    }

}