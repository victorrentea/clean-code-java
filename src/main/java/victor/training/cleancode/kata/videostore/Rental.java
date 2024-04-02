package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {
    public double computePrice() {
        return switch (movie.movieType()) {
            case REGULAR -> regularPrice();
            case NEW_RELEASE -> rentalDays * 3;
            case CHILDREN -> childrenPrice();
        };
    }

    private double childrenPrice() {
        double amount = 1.5;
        if (rentalDays > 2) {
            amount += (rentalDays - 3) * 1.5;
        }
        return amount;
    }

    private double regularPrice() {
        double amount = 2;
        if (rentalDays > 2) {
            amount += (rentalDays - 2) * 1.5;
        }
        return amount;
    }

    // accidental coupling pt ca am extras cod ce semana doar, ca a zis motosapa (IntelliJ)

    public int getFrequentRenterPoints() {
        int result = 1;
        if (isEligibleForExtraPoints()) {
            result++;
        }
        return result;
    }

    private boolean isEligibleForExtraPoints() {
        return movie().movieType() == MovieType.NEW_RELEASE && rentalDays() >= 2;
    }

    public String toBodyLine() {
        // M VC
        return "\t" + movie().title() + "\t" + computePrice();
    }
}
