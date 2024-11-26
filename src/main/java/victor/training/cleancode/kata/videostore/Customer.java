package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        StringBuilder result = addHeaderLines();

        appendAllRentalLines(result);

        return addFooterLines(result).toString();
    }

    private StringBuilder addHeaderLines() {
        return new StringBuilder("Rental Record for " + getName() + "\n");
    }

    private void appendAllRentalLines(StringBuilder result) {
        result.append(rentals.stream().map(this::generateRentalLine).collect(Collectors.joining()));
    }

    private double getTotalOwedAmount() {
        return rentals.stream().mapToDouble(rental -> rental.movie().calcRentalCost(rental.daysRented())).sum();
    }

    private String generateRentalLine(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.movie().calcRentalCost(rental.daysRented()) + "\n";
    }

    private StringBuilder addFooterLines(StringBuilder result) {
        double totalOwedAmount = getTotalOwedAmount();

        return result.append(String.format("Amount owed is %.1f\nYou earned %d frequent renter points", totalOwedAmount,
                frequentRenterPoints));
    }
}