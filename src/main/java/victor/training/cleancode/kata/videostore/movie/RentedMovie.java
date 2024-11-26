package victor.training.cleancode.kata.videostore.movie;

public abstract class RentedMovie {

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
}