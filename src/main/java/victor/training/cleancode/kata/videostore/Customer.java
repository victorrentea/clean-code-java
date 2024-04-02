package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class Customer {
    String name;
    List<Rental> rentals = new ArrayList<>(); // preserves order

    public void addRental(Movie movie, int price) {
        rentals.add(new Rental(movie, price));
    }

    public String statement() {
        double totalAmount;
        String result = "Rental Record for " + getName() + "\n";
        totalAmount = rentals.stream().mapToDouble(Rental::computePrice).sum();

        for (Rental rental : rentals) {
            double thisAmount;

            // determine amounts for every line
            thisAmount = rental.computePrice();
            result += "\t" + rental.movie().title() + "\t" + thisAmount + "\n";
        }

        int frequentRenterPoints;
        frequentRenterPoints = rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();

        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;

    }
}