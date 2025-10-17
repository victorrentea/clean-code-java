package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    double computeRentalPrice() {
        return movie.moviePricingCategory().computeMovieRentalPrice(rentalDays);
    }
}
