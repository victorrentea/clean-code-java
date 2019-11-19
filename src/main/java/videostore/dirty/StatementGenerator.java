package videostore.dirty;

import java.util.List;
import java.util.stream.Collectors;

public class StatementGenerator {
    public String generateStatement(String customerName, List<Rental> rentals) {
        return generateHeader(customerName) +
                generateBody(rentals) +
                generateFooter(rentals);
    }

    private String generateFooter(List<Rental> rentals) {
        return "Amount owed is " + computeTotalPrice(rentals) + "\n"
                + "You earned " + computeTotalFrequentRentalPoints(rentals) + " frequent renter points";
    }

    private String generateBody(List<Rental> rentals) {
        return rentals.stream().map(this::generateStatementLine).collect(Collectors.joining());
    }

    private int computeTotalFrequentRentalPoints(List<Rental> rentals) {
        return rentals.stream().mapToInt(Rental::computeFrequentRentalPoints).sum();
    }

    private double computeTotalPrice(List<Rental> rentals) {
        return rentals.stream().mapToDouble(Rental::computePrice).sum();
    }

    private String generateStatementLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
    }

    private String generateHeader(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

}
