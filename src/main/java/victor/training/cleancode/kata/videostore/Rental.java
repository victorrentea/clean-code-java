package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, Integer days){

    double getPrice() {
        return switch (movie.priceCode()) {
            case REGULAR -> regularPrice();
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

    private double regularPrice() {
        double price = 2;
        if (days > 2) {
            price += (days - 2) * 1.5;
        }
        return price;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        if (movie.priceCode() == PriceCode.NEW_RELEASE && days >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public String toString() {
        return "\t%s\t%s".formatted(movie.title(), getPrice());
    }
}
