package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

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

        double totalAmount = rentedMovies.stream().mapToDouble(Customer::computeAmountForRental).sum();

        for (RentedMovie rentedMovie : rentedMovies) {
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two-day new release rental
            if (isATwoDayNewReleaseRental(rentedMovie)) {
                frequentRenterPoints++;
            }
        }

		for (RentedMovie rentedMovie : rentedMovies) {
            // show figures line for this rental
            result += "\t" + rentedMovie.movie().title() + "\t" + computeAmountForRental(rentedMovie) + "\n";
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

    private static double computeAmountForRental(RentedMovie rentedMovie) {
        double thisAmount = 0;
        // determine amounts for every line
        switch (rentedMovie.moviePriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (rentedMovie.rentalDays() > 2)
                    thisAmount += (rentedMovie.rentalDays() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += rentedMovie.rentalDays() * 3;
                break;
            case CHILDREN:
                thisAmount += 1.5;
                if (rentedMovie.rentalDays() > 3)
                    thisAmount += (rentedMovie.rentalDays() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}