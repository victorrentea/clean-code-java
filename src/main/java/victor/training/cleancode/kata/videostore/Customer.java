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
		int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // loop over each movie rental

        double totalAmount = rentedMovies.stream().mapToDouble(RentedMovie::computeRentalPrice).sum();

        for (RentedMovie rentedMovie : rentedMovies) {
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two-day new release rental
            if (isATwoDayNewReleaseRental(rentedMovie)) {
                frequentRenterPoints++;
            }
        }

        String title = rentedMovies.stream().map(rentedMovie -> rentedMovie.toString()).collect(Collectors.joining("\n"));

		for (RentedMovie rentedMovie : rentedMovies) {
            // show figures line for this rental
            result += rentedMovie.formatForInvoice();
		}

		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}


    private static boolean isATwoDayNewReleaseRental(RentedMovie rentedMovie) {
        return rentedMovie.moviePriceCode() != null &&
              (rentedMovie.moviePriceCode() == PriceCode.NEW_RELEASE)
              && rentedMovie.rentalDays() > 1;
    }

}