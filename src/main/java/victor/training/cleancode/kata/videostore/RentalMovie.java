package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

public record RentalMovie(Movie movie, int days) {
    private static double calculateRentalCost(int rentalDays, int days, double initialAmount) {
        if (rentalDays > days)
            return initialAmount + (rentalDays - days) * 1.5;
        return initialAmount;
    }

    public boolean shouldAddBonus() {
        return movie.priceCode() == NEW_RELEASE && days > 1;
    }

    public double getRentalCostByPriceCode() {
        return switch (movie.priceCode()) {
            case REGULAR -> calculateRentalCost(days, 2, 2);
            case NEW_RELEASE -> days * 3;
            case CHILDREN -> calculateRentalCost(days, 3, 1.5);
        };
    }
}
