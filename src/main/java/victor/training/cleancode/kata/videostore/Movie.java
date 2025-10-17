package victor.training.cleancode.kata.videostore;

public record Movie(String title, MoviePricingCategory priceCode) {
    double getCost(int daysRented) {
        return switch (priceCode) {
            case REGULAR -> regularCost(daysRented);
            case NEW_RELEASE -> daysRented * 3;
            case CHILDRENS -> childrenPrice(daysRented);
        };
    }

    private static double childrenPrice(int daysRented) {
        double movieCost = 1.5;
        if (daysRented > 3)
            movieCost += (daysRented - 3) * 1.5;
        return movieCost;
    }

    private static double regularCost(int daysRented) {
        double movieCost = 2;
        if (daysRented > 2)
            movieCost += (daysRented - 2) * 1.5;
        return movieCost;
    }
}