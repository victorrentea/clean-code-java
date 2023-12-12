package victor.training.cleancode.kata.videostore.horror;

public class Rental {

    private final Movie movie;
    private final int rentalDays;

    public Rental(Movie movie, int rentalDays) {
        this.movie = movie;
        this.rentalDays = rentalDays;
    }

    double getTotalAmount() {
        int dr = getRentalDays();
        double thisAmount = 0;
        switch (getMovie().getCategory()) {
            case REGULAR:
                thisAmount += 2;
                if (dr > 2)
                    thisAmount += (dr - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += dr * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (dr > 3)
                    thisAmount += (dr - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    int updateTotalPoints(Rental rental, int totalPoints) {
        totalPoints++;
        if (rental.getMovie().getCategory().equals(Category.NEW_RELEASE)
                && rental.getRentalDays() > 1)
            totalPoints++;
        return totalPoints;

    }

    public Movie getMovie() {
        return movie;
    }

    public int getRentalDays() {
        return rentalDays;
    }

}
