package victor.training.cleancode.kata.videostore;

import java.util.List;
import java.util.stream.Collectors;

public record Statement(Customer customer, double totalAmount, int frequentRenterPoints,
                        List<RentalAmount> rentalAmounts) {

    @Override
    public String toString() {
        String result = "Rental Record for " + customer.getName() + "\n";

        result += rentalAmounts.stream().map(rentalAmount ->
                "\t" + rentalAmount.movie().title() + "\t" + rentalAmount.amount() + "\n"
        ).collect(Collectors.joining());

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
