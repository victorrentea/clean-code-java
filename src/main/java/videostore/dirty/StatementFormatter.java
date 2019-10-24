package videostore.dirty;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class StatementFormatter {
    public String formatStatement(List<Rental> rentals, String customerName) {
        return formatHeader(customerName) +
                formatBody(rentals) +
                formatFooter(rentals);
    }

    private String formatBody(List<Rental> rentals) {
        return rentals.stream()
                .map(this::formatBody)
                .collect(joining());
    }

    private String formatFooter(List<Rental> rentals) {
        return "Amount owed is " + calculateTotalPrice(rentals) + "\n" +
                "You earned " + calculateTotalFrequestPoints(rentals) + " frequent renter points";
    }

    private int calculateTotalFrequestPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private double calculateTotalPrice(List<Rental> rentals) {
        return rentals.stream()
                .mapToDouble(Rental::calculateRentalPrice)
                .sum();
    }

    private String formatBody(Rental rental) {
        return String.format("\t%s\t%s%n", rental.getMovie().getTitle(), rental.calculateRentalPrice());
    }

    private String formatHeader(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

}
