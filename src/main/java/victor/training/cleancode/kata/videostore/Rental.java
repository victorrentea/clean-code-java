package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
    double getRentalAmount() {
        return movie().priceCode().calculateAmount(daysRented());
    }
}
