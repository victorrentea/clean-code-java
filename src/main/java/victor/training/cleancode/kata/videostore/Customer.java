package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new LinkedList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

    public String statement() {
        var totalAmount = rentals.stream().mapToDouble(Rental::getPrice).sum();

        int frequentRenterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for %s\n".formatted(getName()));

        result.append(rentals.stream().map(rental -> "\t%s\t%s\n".formatted(rental.movie().title(), rental.getPrice())).collect(Collectors.joining()));

        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints = getFrequentRenterPoints(rental, frequentRenterPoints);
            // show figures line for this rental
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private static int getFrequentRenterPoints(Rental rental, int frequentRenterPoints) {
        frequentRenterPoints++;
        // add bonus for a two-day new release rental
        if (rental.movie().priceCode() == PriceCode.NEW_RELEASE && rental.days() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

}
