package videostore.horror;

import java.util.ArrayList;
import java.util.List;

import static videostore.horror.Movie.PriceCode.NEW_RELEASE;


class Customer {
    private final String name;
    private final List<MovieRental> rentals = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new MovieRental(movie, daysRented));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (MovieRental rental : rentals) {

            double moviePrice = rental.calculateMoviePrice();
// add frequent renter points
            var movieRenterPoints = calculateRenterPoints(rental);
            frequentRenterPoints += movieRenterPoints;
// show figures line for this rental
            result += "\t" + rental.movie().title() + "\t" + moviePrice + "\n";
            totalAmount += moviePrice;
        }
// add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static int calculateRenterPoints(MovieRental rental) {
        int result = 1;

        if ((rental.movie().priceCode() == NEW_RELEASE)
                && rental.daysRented() >= 2) {
            result++;
        }
        return result;
    }

}