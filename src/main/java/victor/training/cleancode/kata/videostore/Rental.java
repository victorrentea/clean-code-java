package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, Integer days){

    double getPrice() {
        return switch (movie.priceCode()) {
            case REGULAR -> {
                double price = 2;
                if (days > 2) {
                    price += (days - 2) * 1.5;
                }
                yield price;
            }
            case NEW_RELEASE -> days * 3;
            case CHILDREN -> {
                double price = 1.5;
                if (days > 3) {
                    price += (days - 3) * 1.5;
                }
                yield price;
            }
        };
    }
    int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        // add bonus for a two-day new release rental
        if (movie.priceCode() == PriceCode.NEW_RELEASE && days > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public String toString() {
        return "\t%s\t%s".formatted(movie.title(), getPrice());
    }
}
