package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

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
        int renterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        // loop over each movie rental
        for (Movie movie : rentals.keySet()) {
            double totalPrice = 0;
            int numberOfRentals = rentals.get(movie);

            totalPrice += calculatePrice(numberOfRentals, movie.category);

            renterPoints += 1 + calculateBonus(movie, numberOfRentals);
            // show figures line for this rental
            result += "\t" + movie.getTitle() + "\t" + totalPrice + "\n";
            totalAmount += totalPrice;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + renterPoints + " frequent renter points";
        return result;
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