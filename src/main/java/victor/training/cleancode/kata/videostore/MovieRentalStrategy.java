package victor.training.cleancode.kata.videostore;

// Strategy Pattern
public interface MovieRentalStrategy {
    double calculatePrice(int rentalDays);
    int calculateFrequentRenterPoints(int rentalDays);
}
