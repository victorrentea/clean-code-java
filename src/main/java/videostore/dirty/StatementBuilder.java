package videostore.dirty;

import java.util.List;
import java.util.stream.Collectors;

public class StatementBuilder {
        public String build(String customerName, List<Rental> rentals) {
                return buildStatementHeader(customerName)
                        + buildStatementBody(rentals)
                        + buildStatementFooter(rentals);
        }

        private String buildStatementFooter(List<Rental> rentals) {
                return "Amount owed is " + computeTotalPrice(rentals) + "\n"
                        + "You earned " + computeTotalFrequentRenterPoints(rentals)
                        + " frequent renter points";
        }

        private String buildStatementBody(List<Rental> rentals) {
                return rentals.stream().map(this::buildStatementLine).collect(Collectors.joining());
        }

        private int computeTotalFrequentRenterPoints(List<Rental> rentals) {
                return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
        }

        private double computeTotalPrice(List<Rental> rentals) {
                return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        }

        private String buildStatementHeader(String customerName) {
                return "Rental Record for " + customerName + "\n";
        }

        private String buildStatementLine(Rental rental) {
                return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
        }
}
