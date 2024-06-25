package victor.training.cleancode.kata.videostore;

record MovieRental(Movie movie, int numDays) {

    double getRentalPrice() {
        return switch (movie.priceCode()) {
            case REGULAR -> getRegularPrice(numDays);
            case NEW_RELEASE -> getNewReleasePrice(numDays);
            case CHILDREN -> getChildrenPrice(numDays);
        };
    }

    private static double getChildrenPrice(int numDays) {
        double basePrice = 1.5;
        if (numDays > 3)
            return basePrice + (numDays - 3) * 1.5;
        return basePrice;
    }

    private static double getNewReleasePrice(int numDays) {
        return (double) (numDays * 3);
    }

    private static double getRegularPrice(int numDays) {
        double rentalPrice = 2;
        if (numDays > 2)
            return rentalPrice + (numDays - 2) * 1.5;
        return rentalPrice;
    }

}
