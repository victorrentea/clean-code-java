package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
    double calculatePrice() {
        return movie().priceCode().calculatePrice(daysRented());
    }
}
