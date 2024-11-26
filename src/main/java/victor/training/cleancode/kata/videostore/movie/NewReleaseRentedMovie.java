package victor.training.cleancode.kata.videostore.movie;

public class NewReleaseRentedMovie extends RentedMovie {

    public NewReleaseRentedMovie(String title, int rentedDays) {
        super(title, rentedDays);
    }

    @Override
    boolean isEligibleForBonusPoints() {
        return rentedDays > 1;
    }

    @Override
    double computePrice() {
        return rentedDays * 3;
    }
}
