package victor.training.cleancode.kata.videostore;

import java.util.Map;
import java.util.stream.Collectors;

public record Statement(Customer customer, double totalAmount, int frequentRenterPoints,
                        Map<Movie, Double> rentalAmounts) {

    @Override
    public String toString() {
        String result = "Rental Record for " + customer.getName() + "\n";

        result += rentalAmounts.entrySet().stream().map(movieDoubleEntry ->
                "\t" + movieDoubleEntry.getKey().title() + "\t" + movieDoubleEntry.getValue() + "\n"
        ).collect(Collectors.joining());

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
