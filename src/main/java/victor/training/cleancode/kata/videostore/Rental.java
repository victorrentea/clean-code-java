package victor.training.cleancode.kata.videostore;

public record Rental(
    Movie movie,
    int daysRented) {
    private static double calculatePrice(Rental rental) {
        int daysRented = rental.daysRented();
        return switch (rental.movie().priceCode()) {
            case REGULAR -> calculateRegularPrice(daysRented);
            case NEW_RELEASE -> daysRented * 3;
            case CHILDREN -> calculateChildrenPrice(daysRented);
            //case BLOCKBUSTER -> 0.0;
        };
    }

    private static double calculateChildrenPrice(int daysRented) {
        final int MIN_DAYS_FOR_EXTRA_PRICE = 3;
        double thisAmount = 1.5;
        if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
            thisAmount += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
        }
        return thisAmount;
    }

    private static double calculateRegularPrice(int daysRented) {
        final int MIN_DAYS_FOR_EXTRA_PRICE = 2;
        double thisAmount = 2;
        if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
            thisAmount += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
        }
        return thisAmount;
    }
}
