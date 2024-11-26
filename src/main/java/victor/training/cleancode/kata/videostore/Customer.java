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
        return "Rental Record for " + getName() + "\n" + getAllRentalLines() + addFooterLines();
    }

    private String getAllRentalLines() {
        return rentals.stream().map(this::generateRentalLine).collect(Collectors.joining());
    }

    private double getTotalOwedAmount() {
        return rentals.stream().mapToDouble(rental -> rental.calculateCost(rental.daysRented())).sum();
    }

    private String generateRentalLine(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.calculateCost(rental.daysRented()) + "\n";
    }

    private String addFooterLines() {
        double totalOwedAmount = getTotalOwedAmount();

        return String.format("Amount owed is %.1f\nYou earned %d frequent renter points", totalOwedAmount,
                frequentRenterPoints);
    }
}