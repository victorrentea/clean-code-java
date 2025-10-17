package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
	private final String name;
	private final List<RentedMovie> rentedMovies = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int rentalDays) {
        rentedMovies.add(new RentedMovie(movie, rentalDays));
	}

	public String getName() {
		return name;
	}

	public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        result += rentedMovies.stream().map(RentedMovie::formatForInvoice).collect(Collectors.joining());

		// add footer lines
		result += "Amount owed is " + computeTotalRentalAmount(rentedMovies) + "\n";
		result += "You earned " + computeFrequentRenterPoints(rentedMovies) + " frequent renter points";
		return result;
	}

    private double computeTotalRentalAmount(List<RentedMovie> rentedMovies) {
        return rentedMovies.stream().mapToDouble(RentedMovie::computeRentalPrice).sum();
    }

    private int computeFrequentRenterPoints(List<RentedMovie> rentedMovies) {
        return (int) (rentedMovies.size() + rentedMovies.stream().filter(RentedMovie::isMultidayNewReleaseRental).count());
    }


}