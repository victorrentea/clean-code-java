package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie rental, int daysRented) {
        rentals.add(new Rental(rental, daysRented));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        for (Rental rental : rentals) {
            Movie movie = rental.movie();
            int daysRented = rental.daysRented();

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
        final int MIN_DAYS_FOR_EXTRA_PRICE = 3;
        double thisAmount = 1.5;
        if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
            thisAmount += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
        }
        return thisAmount;
    }

    private static double calculateRegularPrice(int daysRented) {
        final int MIN_DAYS_FOR_EXTRA_PRICE = 2;
        double thisAmount = 2;
        if (daysRented > MIN_DAYS_FOR_EXTRA_PRICE) {
            thisAmount += (daysRented - MIN_DAYS_FOR_EXTRA_PRICE) * 1.5;
        }
        return thisAmount;
    }
}