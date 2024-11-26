package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
    public double getMovieRentalFee() {
        PriceCode priceCode = movie().priceCode();
        double rentalFee = priceCode.baseFee;
        if (daysRented > priceCode.baseFeeDays)
            rentalFee += (daysRented - priceCode.baseFeeDays) * priceCode.additionalFeeRate;
        return rentalFee;
    }
}
