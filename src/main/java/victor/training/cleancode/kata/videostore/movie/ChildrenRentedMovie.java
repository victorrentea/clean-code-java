package victor.training.cleancode.kata.videostore.movie;

public class ChildrenRentedMovie extends RentedMovie {

    public ChildrenRentedMovie(String title, int rentedDays) {
        super(title, rentedDays);
    }

    @Override
    boolean isEligibleForBonusPoints() {
        return false;
    }

    @Override
    double computePrice() {
        double thisAmount = 1.5;
        if (rentedDays > 3) {
            thisAmount += (rentedDays - 3) * 1.5;
        }
        return thisAmount;
    }
}
