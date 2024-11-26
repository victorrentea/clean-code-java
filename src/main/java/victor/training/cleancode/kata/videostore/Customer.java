package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Customer {
    private final String name;

    private final List<Rental> rentals = new ArrayList<>();
    private int frequentRenterPoints;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movieToRent, int daysRented) {
        frequentRenterPoints += movieToRent.calcRenterPoints(daysRented);
        rentals.add(new Rental(movieToRent, daysRented));
    }

    public String generateRentalStatement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        double totalOwedAmount = getTotalOwedAmount();

        appendAllRentalLines(result);

        return addFooterLines(result, totalOwedAmount).toString();
    }

    private void appendAllRentalLines(StringBuilder result) {
        rentals.stream().map(this::generateRentalLine).forEach(result::append);
    }

    private double getTotalOwedAmount() {
        return rentals.stream().mapToDouble(rental -> rental.movie().calcRentalCost(rental.daysRented())).sum();
    }

    private String generateRentalLine(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.movie().calcRentalCost(rental.daysRented()) + "\n";
    }

    private StringBuilder addFooterLines(StringBuilder result, double totalOwedAmount) {
        return result.append("Amount owed is ").append(totalOwedAmount).append("\n").append("You earned ")
                .append(frequentRenterPoints).append(" frequent renter points");
    }
}