package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int days) {

     double computeRentalPrice() {
        double thisAmount = 0;
        switch (movie.priceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (days > 2)
                    thisAmount += (days - 2) * 1.5;
                return thisAmount;
            case NEW_RELEASE:
                thisAmount += days * 3;
                return thisAmount;
            case CHILDREN:
                thisAmount += 1.5;
                if (days > 3)
                    thisAmount += (days - 3) * 1.5;
                return thisAmount;
        }
        return thisAmount;
    }
}
