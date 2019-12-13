package videostore.horror;

import java.util.List;

public class StatementFormatter {

    public String statement(String customerName, List<Rental> rentals) {
        double totalPrice = 0;
        int frequentRenterPoints = 0;
        String result = formatHeader(customerName);

        for (Rental rental : rentals) {
            frequentRenterPoints += rental.calculateFrequentRenterPoints();
            result += formatBodyLine(rental);
            totalPrice += rental.calculatePrice();
        }
        result += formatFooter(totalPrice, frequentRenterPoints);
        return result;
    }

    private String formatBodyLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.calculatePrice() + "\n";
    }

    private String formatHeader(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

    private String formatFooter(double totalPrice, int frequentRenterPoints) {
        String result = "Amount owed is " + totalPrice + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
