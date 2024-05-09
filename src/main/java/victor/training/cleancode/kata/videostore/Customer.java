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
        return header() + getBody() + footer(frequentRenterPoints, totalAmount);
    }

    private String getBody() {
        return rentals.stream()
            .map(Rental::getText)
            .collect(Collectors.joining(""));
    }

    private String header() {
        return "Rental Record for " + getName() + "\n";
    }

    private String footer(int frequentRenterPoints, double totalAmount) {
        return "Amount owed is " + totalAmount + "\n" + "You earned "
               + frequentRenterPoints + " frequent renter points";
    }

}