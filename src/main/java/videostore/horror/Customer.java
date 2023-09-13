package videostore.horror;

import java.util.*;

import static videostore.horror.PriceCode.NEW_RELEASE;

record Rental {
    Movie movie;
    int daysRented;
}

class Customer {
    private final String name;
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    ;

    public void addRental(Movie m, int d) {
        rentals.put(m, d);
    }

    public String getName() {
        return name;
    }

    // test
    public String statement() {
        double totalPrice = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Movie movie : rentals.keySet()) {
            int daysRented = rentals.get(movie);
            totalPrice += calculatePrice(movie.priceCode(), daysRented);

            frequentRenterPoints += calculatePoints(movie, daysRented);

            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + calculatePrice(movie.priceCode(), daysRented) + "\n";
        }
        // add footer lines
        result += "Amount owed is " + totalPrice + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static int calculatePoints(Movie movie, int daysRental) {
        int frequentRenterPoints = 1;
        if ((movie.priceCode() == NEW_RELEASE) && daysRental >= 2)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private static double calculatePrice(PriceCode priceCode, final int daysRented) {
        switch (priceCode) {
            case REGULAR -> {
                return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
            }
            case NEW_RELEASE -> {
                return daysRented * 3;
            }
            case CHILDREN -> {
                double price = 1.5;
                if (daysRented > 3)
                    price += (daysRented - 3) * 1.5;
                return price;
            }
            default -> throw new IllegalStateException("Unexpected value: " + priceCode);
        }
    }
}