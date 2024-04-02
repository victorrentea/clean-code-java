package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {

    public double computePrice() {
        double thisAmount = 0;
        switch (movie.movieType()) {

            case REGULAR -> {
                thisAmount += 2;
                if (rentalDays > 2)
                    thisAmount += (rentalDays - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += rentalDays * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (rentalDays > 3)
                    thisAmount += (rentalDays - 3) * 1.5;
            }
        }
        return thisAmount;
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
