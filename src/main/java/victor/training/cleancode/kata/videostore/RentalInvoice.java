package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RentalInvoice {
	private final String name;
	private final List<MovieRental> movieRentals = new ArrayList<>();

	public RentalInvoice(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int rentalDays) {
        movieRentals.add(new MovieRental(movie, rentalDays));
	}

    public String statement() {
        String result = "Rental Record for " + name + "\n";
        result += movieRentals.stream().map(MovieRental::formatForInvoice).collect(Collectors.joining());
		result += "Amount owed is " + computeTotalRentalAmount() + "\n";
		result += "You earned " + computeFrequentRenterPoints(movieRentals) + " frequent renter points";
		return result;
	}

    private double computeTotalRentalAmount() {
        return movieRentals.stream().mapToDouble(MovieRental::computePrice).sum();
    }

    private long computeFrequentRenterPoints(List<MovieRental> movieRentals) {
        return movieRentals.size() + movieRentals.stream().filter(MovieRental::isEligibleForBonus).count();
    }
}