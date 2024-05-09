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
        String prefix = "Rental Record for " + getName() + "\n";
        String suffix = "Amount owed is " + totalAmount + "\n" + "You earned "
                + frequentRenterPoints + " frequent renter points";
        return rentals.stream().map(Rental::getText).collect(Collectors.joining("", prefix, suffix));
    }

}