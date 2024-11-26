package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static victor.training.cleancode.kata.videostore.Category.NEW_RELEASE;

class Customer {
    private final String name;
    private final Map<Movie, Integer> rentals = new LinkedHashMap<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int numberOfRentals) {
        this.rentals.put(movie, numberOfRentals);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        // loop over each movie rental
        int renterPoints = 0;
        for (Movie movie : rentals.keySet()) {
            double totalPrice = 0;
            int numberOfRentals = rentals.get(movie);
            totalPrice += calculatePrice(numberOfRentals, movie.category);
            renterPoints += 1 + calculateBonus(movie, numberOfRentals);

            result.append("\t").append(movie.getTitle())
                    .append("\t").append(totalPrice).append("\n");
            totalAmount += totalPrice;
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(renterPoints).append(" frequent renter points");
        return result.toString();
    }

    private int calculateBonus(Movie movie, int numberOfRentals) {
        return movie.getPriceCode() != null &&
                (movie.getPriceCode() == NEW_RELEASE.getCode())
                && numberOfRentals > 1 ? 1 : 0;
    }

    private double calculatePrice(int numberOfRentals, Category category) {
        double price = numberOfRentals > category.getDiscountFactor() ?
                (numberOfRentals - category.getDiscountFactor()) * category.getMultiplicationFactor() :
                0;
        return price + category.getBasePrice();
    }
}