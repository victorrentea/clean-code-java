package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int days) {
		rentals.put(movie, days);
	}

	public String getName() {
		return name;
	}

	public String statement() {
        int frequentRenterPoints = (int) (rentals.size() +
                        rentals.keySet().stream()
                                .filter(m -> m.priceCode() == Movie.PriceCode.NEW_RELEASE && rentals.get(m) > 1)
                                .count());
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// loop over each movie rental
        double totalRentalFee = rentals.keySet().stream()
                .mapToDouble(movie -> getRentalFee(movie, rentals.get(movie))).sum();

        rentals.keySet().forEach(movie -> result.append("\t").append(movie.title()).append("\t")
                .append(getRentalFee(movie, rentals.get(movie))).append("\n"));
		// add footer lines
		result.append("Amount owed is ").append(totalRentalFee).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
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