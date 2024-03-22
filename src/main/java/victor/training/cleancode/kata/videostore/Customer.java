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
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            final Movie movie = rental.movie();
            if (movie.getPriceCode() != null && (movie.getPriceCode() == Category.NEW_RELEASE) && rental.daysRented() > 1) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + movie.getTitle() + "\t" + amount + "\n";
            totalAmount += amount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;

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
