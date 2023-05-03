package videostore.horror;

import static videostore.horror.MovieCategory.NEW_RELEASE;

public class Rental {

    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double computePrice() {
        return movie.getCategory().computePrice(daysRented);
        //        switch (movie.getCategory()) {
        //            case REGULAR:
        //                return computeRegularPrice();
        //            case NEW_RELEASE:
        //                return computeNewReleasePrice();
        //            case CHILDREN:
        //                return computeChildrenPrice();
        //            default:
        //                throw new IllegalStateException("Unexpected value: " + movie.getCategory());
        //        }
    }

    private double computeChildrenPrice() {
        double price = 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }

    private int computeNewReleasePrice() {
        return daysRented * 3;
    }

    private double computeRegularPrice() {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getFrequentRenterPoints() {
        int result = 1;
        boolean isEligibleForBonus = movie.getCategory() == NEW_RELEASE && daysRented >= 2;
        if (isEligibleForBonus) {
            result++;
        }
        return result;
    }
}
