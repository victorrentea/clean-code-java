package videostore.horror;

import static videostore.horror.PriceCode.NEW_RELEASE;

public class Rental {
    private final Movie movie;
    private final int days;

    public Rental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }

    public int calculateFrequentRenterPoints() {
//		int frequentRenterPoints = 1;
//		if (movie.priceCode() == NEW_RELEASE && daysRented >= 2) frequentRenterPoints++;
//		return frequentRenterPoints;

        return getMovie().priceCode() == NEW_RELEASE && getDays() >= 2 ? 2 : 1;
    }

    public double calculateAmount() {
        double thisAmount = 0;
        int daysRented = getDays();
        switch (getMovie().priceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += daysRented * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
            }
        }
        return thisAmount;
    }
}
