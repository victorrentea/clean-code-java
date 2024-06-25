package victor.training.cleancode.kata.videostore;

record MovieRental(Movie movie, int numDays) {

    int getFrequentRenterPoints() {
        if (movie().priceCode() == Movie.PriceCode.NEW_RELEASE && numDays() > 1) {
            return 2;
        }
        return 1;
    }

    double getRentalPrice() {
        return switch (movie.priceCode()) {
            case REGULAR -> getRegularPrice();
            case NEW_RELEASE -> getNewReleasePrice();
            case CHILDREN -> getChildrenPrice();
        };
    }

    private double getChildrenPrice() {
        double basePrice = 1.5;
        if (numDays > 3)
            return basePrice + (numDays - 3) * 1.5;
        return basePrice;
    }

    private double getNewReleasePrice() {
        return (numDays * 3);
    }

    private double getRegularPrice() {
        double rentalPrice = 2;
        if (numDays > 2)
            return rentalPrice + (numDays - 2) * 1.5;
        return rentalPrice;
    }

}
