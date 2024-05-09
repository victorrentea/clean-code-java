package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    @Getter
    private String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order!

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {
        double price = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Rental rental : rentals) {
            // determine amounts for every line
            int daysRented = rental.daysRented();
            double owedAmount = getOwedAmount(rental);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two days new release rental
            if (rental.movie().priceCode() == NEW_RELEASE && daysRented >= 2) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + rental.movie().title() + "\t" + owedAmount + "\n";
            price += owedAmount;
        }
        // add footer lines
        result += "Amount owed is " + price + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double getOwedAmount(Rental rental) {
        int daysRented = rental.daysRented();
        return switch (rental.movie().priceCode()) {
            case REGULAR -> getRegularPrice(daysRented);
            case NEW_RELEASE -> getNewReleasePrice(daysRented);
            case CHILDRENS -> getChildrenPrice(daysRented);
        };
    }

    private static double getChildrenPrice(int daysRented) {
        double price = 1.5;
        if (daysRented > 3) {
            price += (daysRented - 3) * 1.5;
        }
        return price;
    }

    private static int getNewReleasePrice(int daysRented) {
        return daysRented * 3;
    }

    private static double getRegularPrice(int daysRented) {
        double price = 2;
        if (daysRented > 2) {
            price += (daysRented - 2) * 1.5;
        }
        return price;
    }
}