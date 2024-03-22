package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

import lombok.Getter;

class Customer {
    @Getter
    private String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }



    public void addRental(Movie movie, Integer daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {

        double totalAmount = rentals.stream()
                .mapToDouble(Rental::calculateAmount)
                .sum();
        int frequentRenterPoints = rentals.stream()
                .mapToInt(this::calculateFrequentRenterPoints)
                .sum();

        String result = rentals.stream()
                .map(rental -> "\t" + rental.movie().title() + "\t" + rental.calculateAmount() + "\n")
                .collect(Collectors.joining());

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return "Rental Record for " + getName() + "\n" + result;

    }

    private int calculateFrequentRenterPoints(Rental rental) {
        int points = 1;
        if (rental.movie().category() == Category.NEW_RELEASE && rental.daysRented() > 1) {
            points++;
        }
        return points;
    }
}
