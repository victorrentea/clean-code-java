package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
    public double calculatePrice() {
        return switch (movie.priceCode()) {
            case REGULAR -> calculateRegularPrice();
            case NEW_RELEASE -> daysRented * 3;
            case CHILDRENS ->
                daysRented > 3 ?  1.5 + (daysRented - 3) * 1.5 : 1.5;
        };
    }

    private double calculateRegularPrice() {
        double thisAmount = 2;
        if (daysRented > 2) thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }

    public int calculateFrequentRenterPoints() {
        return deservesABonus() ? 2 : 1 ;
    }
    private boolean deservesABonus() {
        return movie.priceCode() == PriceCode.NEW_RELEASE && daysRented > 1;
    }
}
