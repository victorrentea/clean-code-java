package victor.training.cleancode.kata.videostore;

public record Statement(double totalAmount, int frequentRenterPoints, Map<Movie, Double> rentalAmounts) {
}
