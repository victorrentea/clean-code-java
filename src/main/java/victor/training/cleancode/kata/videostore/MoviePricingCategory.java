package victor.training.cleancode.kata.videostore;

enum MoviePricingCategory {
    REGULAR(2,2,1.5),
    CHILDREN(1.5,3,1.5),
    NEW_RELEASE(0,0,3);

    private final double movieBasePrice;
    private final int rentalDaysThresholdForPenalty;
    private final double penaltyMultiplier;

    MoviePricingCategory(double movieBasePrice, int rentalDaysThresholdForPenalty, double penaltyMultiplier) {
        this.movieBasePrice = movieBasePrice;
        this.rentalDaysThresholdForPenalty = rentalDaysThresholdForPenalty;
        this.penaltyMultiplier = penaltyMultiplier;
    }

    double computeMovieRentalPrice(int rentalDays) {
        double movieAmount = movieBasePrice;
        if (rentalDays > rentalDaysThresholdForPenalty) {
            movieAmount += (rentalDays - rentalDaysThresholdForPenalty) * penaltyMultiplier;
        }
        return movieAmount;
    }
}
