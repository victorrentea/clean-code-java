package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.enums.MovieType.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {

    public double computeAmount() {
        return switch (movie.movieType()) {
            case REGULAR -> getAmount(0, 2, 2);
            case NEW_RELEASE -> daysRented * 3;
            case CHILDREN -> getAmount(0, 1.5, 3);
        };
    }

    private double getAmount(double thisAmount, double baseRate, int threshold) {
        thisAmount += baseRate;
        if (daysRented > threshold)
            thisAmount += (daysRented - threshold) * 1.5;
        return thisAmount;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints= 1;
        // add bonus for a two day new release rental
        if (movie.movieType() != null &&
            (movie.movieType() == NEW_RELEASE)
                && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
