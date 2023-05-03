package videostore.horror;

import static videostore.horror.MovieCategory.NEW_RELEASE;

public class Rental {

    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double computeAmount() {
        double thisAmount;
        switch (getMovie().getCategory()) {
            case REGULAR:
                thisAmount = 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                return thisAmount;
            case NEW_RELEASE:
                return getDaysRented() * 3;
            case CHILDREN:
                thisAmount = 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                return thisAmount;

            default:
                throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
        }
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getFrequentRenterPoints() {
        boolean isEligibleForBonus = getMovie().getCategory() == NEW_RELEASE && getDaysRented() >= 2;
        return isEligibleForBonus ? 2 : 1;
    }
}
