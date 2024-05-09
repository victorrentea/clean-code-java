package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.enums.MovieType.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {

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
