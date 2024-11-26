package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.enums.PriceCode;

public class RentedMovie {

    private final String title;
    private final PriceCode movieTypePriceCode;
    private final int rentedDays;
    private final double price;

    public RentedMovie(String title, PriceCode movieTypePriceCode, int rentedDays) {
        this.title = title;
        this.movieTypePriceCode = movieTypePriceCode;
        this.rentedDays = rentedDays;
        this.price= computePrice();
    }

    // @ValidForTests
    boolean isEligibleForBonusPoints() {
        return movieTypePriceCode == PriceCode.NEW_RELEASE && rentedDays > 1;
    }

    public double computePrice() {
        double thisAmount = 0;
        switch (movieTypePriceCode) {
            case REGULAR -> {
                thisAmount += 2;
                if (rentedDays > 2) {
                    thisAmount += (rentedDays - 2) * 1.5;
                }
            }
            case NEW_RELEASE -> thisAmount += rentedDays * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (rentedDays > 3) {
                    thisAmount += (rentedDays - 3) * 1.5;
                }
            }
        }
        return thisAmount;
    }

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