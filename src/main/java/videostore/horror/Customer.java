package videostore.horror;

import java.util.ArrayList;
import java.util.List;


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
        int frequentRenterPoints;
        String result = formatHeader();
        frequentRenterPoints = rentals.stream().mapToInt(MovieRental::calculateRenterPoints).sum();

        for (MovieRental rental : rentals) {
            double moviePrice = rental.calculateMoviePrice(); // functie pura super fast (ca nu face retea)
            result += formatBodyLine(rental, moviePrice);
            totalAmount += moviePrice;
        }
        result += formatFooter(totalAmount, frequentRenterPoints);
        return result;
    }

    private static String formatBodyLine(MovieRental rental, double moviePrice) {
        return "\t" + rental.movie().title() + "\t" + moviePrice + "\n";
    }

    private String formatHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private static String formatFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n" +
               "You earned " + frequentRenterPoints + " frequent renter points";
    }

}