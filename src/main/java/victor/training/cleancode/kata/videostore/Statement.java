package victor.training.cleancode.kata.videostore;

import java.util.Map;

public record Statement(double totalAmount, int frequentRenterPoints, Map<Movie, Double> rentalAmounts, String sorryTemp) {
}
