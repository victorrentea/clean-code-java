package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {
    public int getFrequentRenterPoints() {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if (movie.priceCode() == NEW_RELEASE && daysRented >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
