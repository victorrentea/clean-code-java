package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    private final String name;
    private final List<Rental> rentals = new LinkedList<>(); // preserves order of elements TODO find a better way to store this

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

    public String getName() {
        return name;
    }

    public Statement statement() {
        double totalAmount = 0;
        String result = "Rental Record for " + getName() + "\n";
        // loop over movie rental
		Map<Movie, Double> finalPricePerMovie = new LinkedHashMap<>();
        for (Rental rental : rentals) {
            // determine amounts for every line
            Movie movie = rental.movie();
            double thisAmount = rental.computeRentalPrice();
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + thisAmount + "\n";
            finalPricePerMovie.put(movie, totalAmount);
            totalAmount += thisAmount;
        }
        int frequentRenterPoints = computeFrequentRenterPoints();
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return new Statement(totalAmount, frequentRenterPoints, finalPricePerMovie, result);
    }

    private int computeFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (rental.movie().priceCode() == NEW_RELEASE && rental.days() > 1)
                frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

}