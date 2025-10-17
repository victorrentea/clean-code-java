package victor.training.cleancode.kata.videostore;

public enum PriceCode {
    CHILDREN(1.5, 3, 1.5 ),
    REGULAR(2, 2, 1.5),
    NEW_RELEASE(0, 0, 3);

    private final double shortRentalPrice;
    private final int longRentalThreshold;
    private final double longRentalPricePerDay;
    PriceCode(double shortRentalPrice, int longRentalThreshold, double longRentalPricePerDay) {
        this.shortRentalPrice = shortRentalPrice;
        this.longRentalThreshold = longRentalThreshold;
        this.longRentalPricePerDay = longRentalPricePerDay;
    }

    public double computePrice(int daysRented) {
        double result = shortRentalPrice;
        if(daysRented > longRentalThreshold) {
            result += (daysRented - longRentalThreshold) * longRentalPricePerDay;
        }
        return result;
    }
}
