package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
    private final String name;

    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        int frequentRenterPoints = rentals.stream().filter(Rental::eligibleForBonus).toList().size() + rentals.size();
        double totalAmount = rentals.stream().mapToDouble(Rental::calculateAmount).sum();
        return getRentalDetails(frequentRenterPoints, totalAmount);
    }

    private String getRentalDetails(int frequentRenterPoints, double totalAmount) {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {
            double amountForCurrentRental = rental.calculateAmount();
            result.append("\t").append(rental.movie().title()).append("\t").append(amountForCurrentRental).append("\n");
        }
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}