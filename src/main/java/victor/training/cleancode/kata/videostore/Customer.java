package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new LinkedList<>(); // preserves order of elements TODO find a better way to store this

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

    public Statement statement() {
        double totalAmount = rentals.stream().mapToDouble(Rental::computeRentalPrice).sum();

        Map<Movie, Double> finalPricePerMovie = new LinkedHashMap<>();
        for (Rental rental : rentals) {
            // determine amounts for every line
            double thisAmount = rental.computeRentalPrice();
            finalPricePerMovie.put(rental.movie(), thisAmount);
        }

        int frequentRenterPoints = computeFrequentRenterPoints();
        return new Statement(this, totalAmount, frequentRenterPoints, finalPricePerMovie);
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