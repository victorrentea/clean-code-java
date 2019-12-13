package videostore.horror;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementFormatter {

    public String statement(String customerName, List<Rental> rentals) {
        return formatHeader(customerName) +
                formatBody(rentals) +
                formatFooter(rentals);
    }

    private String formatBody(List<Rental> rentals) {
        return rentals.stream().map(this::formatBodyLine).collect(joining());
    }

    private String formatFooter(List<Rental> rentals) {
        return "Amount owed is " + calculateTotalPrice(rentals) + "\n"
                + "You earned " + calculateTotalRenterPoints(rentals) + " frequent renter points";
    }

    private double calculateTotalPrice(List<Rental> rentals) {
        return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
    }

    private int calculateTotalRenterPoints(List<Rental> rentals) {
        return rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
    }

    private String formatBodyLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
    }

    private String formatHeader(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

}
