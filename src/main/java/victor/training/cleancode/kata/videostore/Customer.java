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
                .mapToDouble(this::calculateAmount)
                .sum();
        int frequentRenterPoints = rentals.stream()
                .mapToInt(this::calculateFrequentRenterPoints)
                .sum();

        String result = rentals.stream()
                .map(rental -> "\t" + rental.movie().getTitle() + "\t" + calculateAmount(rental) + "\n")
                .collect(Collectors.joining());

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return "Rental Record for " + getName() + "\n" + result;

    }

    private double calculateAmount(final Rental rental) {
        double amount = 0;
        final int daysRented = rental.daysRented();
        switch (rental.movie().category) {
        case REGULAR:
            amount = 2;
            if (daysRented > 2) {
                amount += (daysRented - 2) * 1.5;
            }
            break;
        case NEW_RELEASE:
            amount = daysRented * 3;
            break;
        case CHILDREN:
            amount = 1.5;
            if (daysRented > 3) {
                amount += (daysRented - 3) * 1.5;
            }
            break;
        }
        return amount;
    }

    private int calculateFrequentRenterPoints(Rental rental) {
        int points = 1;
        if (rental.movie().getPriceCode() == Category.NEW_RELEASE && rental.daysRented() > 1) {
            points++;
        }
        return points;
    }
}
