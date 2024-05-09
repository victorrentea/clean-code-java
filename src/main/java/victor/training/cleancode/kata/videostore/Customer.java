package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    @Getter
    private String name;
    private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order!

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.put(movie, daysRented);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Movie movie : rentals.keySet()) {
            // determine amounts for every line
            int daysRented = rentals.get(movie);
            double owedAmount = getOwedAmount(movie);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two days new release rental
            if (movie.priceCode() == NEW_RELEASE && daysRented >= 2) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + owedAmount + "\n";
            totalAmount += owedAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double getOwedAmount(Movie movie) {
        int daysRented = rentals.get(movie);
        double thisAmount = 0;
        switch (movie.priceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (daysRented > 2) {
                    thisAmount += (daysRented - 2) * 1.5;
                }
            }
            case NEW_RELEASE -> thisAmount += daysRented * 3;
            case CHILDRENS -> {
                thisAmount += 1.5;
                if (daysRented > 3) {
                    thisAmount += (daysRented - 3) * 1.5;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + movie.priceCode());
        }
        return thisAmount;
    }
}