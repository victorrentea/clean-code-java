package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    public double calculatePrice() {
        // Dear Victor, we love you. We know that this can be extracted
        // to meaningful methods which are more readable,
        // but this is enough for today. We shall fix it on Monday™️

        // we added this already to the tech debt backlog.
        // The place wehere all good ideas go to die. without us feeling remorse.
        return switch (movie.movieType()) {
            case REGULAR -> calculateRegularPrice();
            case NEW_RELEASE -> rentalDays * 3.0;
            case CHILDREN -> calculateChildrenPrice();
        };
    }

    private double calculateChildrenPrice() {
        //more verbose but easier to understand. and closer to the spec and the PO 💖
        double basePrice = 1.5;
        if (rentalDays > 3) {
            basePrice += (rentalDays - 3) * 1.5;
        }
        return basePrice;
    }

    private double calculateRegularPrice() {
        return (rentalDays <= 2) ? 2 : 2 + (rentalDays - 2) * 1.5;
    }

    public int calculateFrequentRenterPoints() {
        return isEligibleForBonus() ? 2 : 1;
    }

    private boolean isEligibleForBonus() {
        return movie.movieType() == MovieType.NEW_RELEASE && rentalDays >= 2;
    }
}
