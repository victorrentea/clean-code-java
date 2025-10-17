package victor.training.cleancode.kata.videostore;

public record RentedMovie(Movie movie, int rentalDays) {
    PriceCode moviePriceCode() {
        return movie.priceCode();
    }
}
