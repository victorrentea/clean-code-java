package victor.training.cleancode.kata.videostore.movie;

import victor.training.cleancode.kata.videostore.enums.MovieType;

public abstract class RentedMovie /*permits*/ {  // Sealed
    protected final String title;
    protected final int rentedDays;
    protected final double price;

    public RentedMovie(String title, int rentedDays) {
        this.title = title;
        this.rentedDays = rentedDays;
        this.price= computePrice();
    }

    abstract boolean isEligibleForBonusPoints();

    abstract double computePrice();

    public String getPrintableTitleAndPrice() {
        return "\t" + title + "\t" + price;
    }

    public int getFrequentRenterPoints() {
        if (isEligibleForBonusPoints()) {
            return 2;
        }
        return 1;
    }

    public double getPrice() {
        return price;
    }

    public static RentedMovie create(String title, int rentedDays, MovieType movieType) {
        return switch (movieType) {
            case REGULAR -> new RegularRentedMovie(title, rentedDays);
            case NEW_RELEASE -> new NewReleaseRentedMovie(title, rentedDays);
            case CHILDREN -> new ChildrenRentedMovie(title, rentedDays);
        };
    }

}