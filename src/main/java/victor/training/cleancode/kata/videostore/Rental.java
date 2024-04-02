package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    public double computePrice() {
        double thisAmount = 0;
        switch (movie.movieType()) {
            case REGULAR -> thisAmount += computeAmount(2, 2);
            case NEW_RELEASE -> thisAmount += rentalDays * 3;
            case CHILDREN -> thisAmount += computeAmount(1.5, 3);
        }
        return thisAmount;
    }

    private double computeAmount(double amount, int x) {
        if (rentalDays > 2)
            amount += (rentalDays - x) * 1.5;
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
        return "\t" + this.movie().title() + "\t" + this.computePrice();
    }
}
