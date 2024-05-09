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
      return header() + body() + footer();
    }

    private double totalPrice() {
        return rentals.stream().mapToDouble(Rental::calculateAmount).sum();
    }

    private int totalPoints() {
        return rentals.stream().filter(Rental::eligibleForBonus).toList().size() + rentals.size();
    }

    private String body() {
        return rentals.stream()
            .map(Rental::getText)
            .collect(Collectors.joining(""));
    }

    private String header() {
        return "Rental Record for " + getName() + "\n";
    }

    private String footer() {
        return "Amount owed is " + totalPrice() + "\n" + "You earned "
               + totalPoints() + " frequent renter points";
    }

}