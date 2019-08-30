package videostore.dirty;

import java.util.List;
import java.util.stream.Collectors;

public class StatementViewer {

    public String statement(String customerName, List<Rental> rentals) {

        int frequentRenterPoints = computeTotalFrequentRenterPoints(rentals);

        double totalPrice = computeTotalPrice(rentals);

        return createHeader(customerName)
                + createBody(rentals)
                + createFooter(totalPrice, frequentRenterPoints);
    }

    private String createBody(List<Rental> rentals) {
        return rentals.stream().map(this::createBodyLine).collect(Collectors.joining());
    }

    private double computeTotalPrice(List<Rental> rentals) {
       return rentals.stream().mapToDouble(Rental::computePrice).sum();
}

    private int computeTotalFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream().mapToInt(Rental::computeFrequentRenterPoints).sum();
    }

    private String createBodyLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
    }

    private String createHeader(String customerName) {
        return "Rental Record for " +customerName + "\n";
    }

    private String createFooter(double totalPrice, int frequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n"
                + "You earned " + frequentRenterPoints + " frequent renter points";
    }
}
