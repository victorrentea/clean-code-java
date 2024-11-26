package victor.training.cleancode.kata.videostore;

import java.util.List;
import java.util.stream.Collectors;

public class StatementView {

    private StatementView() {}

    public static String getCustomerSummary(double totalAmount, int frequentRenterPoints, String customerName, List<Rental> customerRentals) {
        return "Rental Record for " + customerName + "\n" +
                getCustomerRentalsSummary(customerRentals) +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private static String getCustomerRentalsSummary(List<Rental> rentals) {
        return rentals.stream().map(StatementView::getRentalSummary).collect(Collectors.joining());
    }

    private static String getRentalSummary(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";
    }
}
