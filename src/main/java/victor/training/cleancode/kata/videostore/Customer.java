package victor.training.cleancode.kata.videostore;

import java.util.*;

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

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        // copilot give me iteration over map rentals

        for (Rental rental : rentals) {
            double amount = calculateAmount(rental);

            // add frequent renter points
            frequentRenterPoints += getFrequentRenterPoints(rental);

            // show figures line for this rental
            result += "\t" + rental.movie()
                .getTitle() + "\t" + amount + "\n";
            totalAmount += amount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;

    }

    private static int getFrequentRenterPoints(final Rental rental) {
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if (rental.movie().getPriceCode() != null && (rental.movie().getPriceCode() == Category.NEW_RELEASE) && rental.daysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private double calculateAmount(final Rental rental) {
        double amount = 0;
        final Integer daysRented = rental.daysRented();
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
}
