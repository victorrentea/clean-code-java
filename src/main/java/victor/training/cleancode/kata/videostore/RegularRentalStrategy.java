package victor.training.cleancode.kata.videostore;

public class RegularRentalStrategy implements MovieRentalStrategy {
    @Override
    public double calculatePrice(int rentalDays) {
        double thisAmount = 2;
        if (rentalDays > 2) {
            thisAmount += (rentalDays - 2) * 1.5;
        }
        return thisAmount;
    }

    @Override
    public int calculateFrequentRenterPoints(int rentalDays) {
        return 1;
    }
}
