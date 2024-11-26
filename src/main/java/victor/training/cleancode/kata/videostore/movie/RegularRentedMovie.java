package victor.training.cleancode.kata.videostore.movie;

public class RegularRentedMovie extends RentedMovie {

    public RegularRentedMovie(String title, int rentedDays) {
        super(title, rentedDays);
    }

    @Override
    boolean isEligibleForBonusPoints() {
        return false;
    }

    @Override
    double computePrice() {
        double thisAmount = 2;
        if (rentedDays > 2) {
            thisAmount += (rentedDays - 2) * 1.5;
        }
        return thisAmount;
    }
}


