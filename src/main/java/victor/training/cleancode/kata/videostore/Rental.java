package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    public double computePrice() {
        return switch (movie.movieType()) {
            case REGULAR -> computeAmount(2, 2);
            case NEW_RELEASE -> rentalDays * 3;
            case CHILDREN -> computeAmount(1.5, 3);
        };
    }

    private double computeAmount(double amount, final int minDaysOfRentalForExtraCharge) {
        if (rentalDays > 2)
            amount += (rentalDays - minDaysOfRentalForExtraCharge) * 1.5;
        return amount;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;

        if (isEligibleForExtraPoints())
            frequentRenterPoints++;

        return frequentRenterPoints;
    }

    private boolean isEligibleForExtraPoints() {
        return movie().movieType() == MovieType.NEW_RELEASE && rentalDays() > 1;
    }

    @Override
    public String toString() {
        return "\t" + movie().title() + "\t" + computePrice();
    }
}
