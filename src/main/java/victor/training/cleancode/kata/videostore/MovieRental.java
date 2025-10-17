package victor.training.cleancode.kata.videostore;

public record MovieRental(Movie movie, int rentalDays) {
    public double computePrice() {
        return movie.priceCode().computePrice(rentalDays);
    }

    public String formatForInvoice() {
        return "\t" + movie().title() + "\t" + computePrice() + "\n";
    }

    public boolean isEligibleForBonus() {
        return (movie.priceCode() == PriceCode.NEW_RELEASE) && rentalDays() > 1;
    }
}
