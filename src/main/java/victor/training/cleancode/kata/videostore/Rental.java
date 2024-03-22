package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, Integer days){


    double getPrice() {
        double thisAmount = 0;
        switch (movie.priceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (days > 2)
                    thisAmount += (days - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += days * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (days > 3)
                    thisAmount += (days - 3) * 1.5;
            }
        }
        return thisAmount;
    }
    int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        // add bonus for a two-day new release rental
        if (movie.priceCode() == PriceCode.NEW_RELEASE && days > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
