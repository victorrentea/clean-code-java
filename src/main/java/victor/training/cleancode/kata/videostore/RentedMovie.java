package victor.training.cleancode.kata.videostore;

public record RentedMovie(Movie movie, int rentalDays) {
    public double computeRentalPrice() {
        return moviePriceCode().computePrice(rentalDays);
    }

    public String formatForInvoice() {
        return "\t" + movie().title() + "\t" + computeRentalPrice() + "\n";
    }

    PriceCode moviePriceCode() {
        return movie.priceCode();
    }

    public boolean isMultidayNewReleaseRental() {
        return moviePriceCode() != null &&
              (moviePriceCode() == PriceCode.NEW_RELEASE)
              && rentalDays() > 1;
    }
}
