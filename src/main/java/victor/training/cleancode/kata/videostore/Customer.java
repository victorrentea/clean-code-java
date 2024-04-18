package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
    private final String name;
    private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie m, int d) {
        rentals.put(m, d);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        // iterate each rental
        for (Movie movie : rentals.keySet()) {
            
            // determine amounts for every line
            int daysRented = rentals.get(movie);
            
            double rentalPrice = calculatePrice(movie, daysRented);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((movie.priceCode() == PriceCode.NEW_RELEASE)
                    && daysRented > 1) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + rentalPrice + "\n";
            totalAmount += rentalPrice;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static double calculatePrice(Movie movie, int daysRented) {
        return switch (movie.priceCode()) {
            case REGULAR -> calculateRegularPrice(daysRented);
            case NEW_RELEASE -> daysRented * 3;
            case CHILDREN -> calculateChildrenPrice(daysRented);
            //case BLOCKBUSTER -> 0.0;
        };
    }

    private static double calculateChildrenPrice(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3) {
            thisAmount += (daysRented - 3) * 1.5;
        }
        return thisAmount;
    }

    private static double calculateRegularPrice(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2) {
            thisAmount += (daysRented - 2) * 1.5;
        }
        return thisAmount;
    }
}