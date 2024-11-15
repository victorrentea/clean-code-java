package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int duration) {
    double price() {
        double price = 0;
        switch (movie.getPriceCode()) {
            case REGULAR -> {
                price += 2;
                if (duration > 2)
                    price += (duration - 2) * 1.5;
            }
            case NEW_RELEASE -> price += duration * 3;
            case CHILDREN -> {
                price += 1.5;
                if (duration > 3)
                    price += (duration - 3) * 1.5;
            }
        }
        return price;
    }

    int frequentRenterPoints() {
        int points = 1;
        // add bonus for a two day new release rental
        if ((movie().getPriceCode() == PriceCategory.NEW_RELEASE) && duration() > 1)
            points++;
        return points;
    }
}
