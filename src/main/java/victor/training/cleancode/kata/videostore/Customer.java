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

    public void addRental(Movie movie, int rentalDays) {
        rentals.put(movie, rentalDays);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Movie movie : rentals.keySet()) {
            // determine amounts for every line
            int rentalDays = rentals.get(movie);
            double thisAmount = getThisAmount(movie, rentalDays);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (movie.priceCode() != null &&
                    movie.priceCode() == NEW_RELEASE &&
                    rentalDays >= 2) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static double getThisAmount(Movie movie, int dailyRentalPrice) {
        double thisAmount = 0;
        switch (movie.priceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (dailyRentalPrice > 2) thisAmount += (dailyRentalPrice - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += dailyRentalPrice * 3;
            case CHILDRENS -> {
                thisAmount += 1.5;
                if (dailyRentalPrice > 3) thisAmount += (dailyRentalPrice - 3) * 1.5;
            }
            default -> throw new IllegalStateException("Unexpected value: " + movie.priceCode());
        }
        return thisAmount;
    }
}