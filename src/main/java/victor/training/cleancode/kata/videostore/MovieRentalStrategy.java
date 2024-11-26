package victor.training.cleancode.kata.videostore;

public interface MovieRentalStrategy {
    double calculatePrice(int rentalDays);
    int calculateFrequentRenterPoints(int rentalDays);
}
