package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    public double calculatePrice() {
        // Dear Victor, we love you. We know that this can be extracted to meaningful methods which are more readable, but this is enough for today.
        return switch (movie.movieType()) {
            case REGULAR -> (rentalDays <= 2) ? 2 : 2 + (rentalDays - 2) * 1.5;
            case NEW_RELEASE -> rentalDays * 3.0;
            case CHILDREN -> (rentalDays <= 3) ? 1.5 : 1.5 + (rentalDays - 3) * 1.5;
        };
    }

    public int calculateFrequentRenterPoints() {
        return isEligibleForBonus() ? 2 : 1;
    }

    private boolean isEligibleForBonus() {
        return movie.movieType() == MovieType.NEW_RELEASE && rentalDays >= 2;
    }
}
