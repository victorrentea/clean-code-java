package victor.training.cleancode.kata.videostore;

public class NewReleaseRentalStrategy implements MovieRentalStrategy {
    @Override
    public double calculatePrice(int rentalDays) {
        return rentalDays * 3;
    }

    @Override
    public int calculateFrequentRenterPoints(int rentalDays) {
        return rentalDays > 1 ? 2 : 1;
    }
}
