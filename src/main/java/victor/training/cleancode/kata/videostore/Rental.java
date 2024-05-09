package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.enums.MovieType.NEW_RELEASE;

public record Rental(Movie movie, int daysRented) {

    public double computeAmount() {
        double thisAmount=0;

        switch (movie.movieType()) {
            case REGULAR:
                thisAmount = getRegularAmount(thisAmount, daysRented, 2, 2);
                break;
            case NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case CHILDREN:
                thisAmount = getRegularAmount(thisAmount, daysRented, 1.5, 3);
                break;
        }
        return thisAmount;
    }

    private double getRegularAmount(double thisAmount, int dr, double i, int i2) {
        thisAmount += i;
        if (dr > i2)
            thisAmount += (dr - i2) * 1.5;
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
