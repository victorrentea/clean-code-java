package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.PriceCode.*;

class Customer {
    private final String name;
    private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements TODO find a better way to store this

    public Customer(String name) {
        this.name = name;
    }


    public void addRental(Movie m, int price) {
        rentals.put(m, price);
    }

    public String getName() {
        return name;
    }

    public Statement statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // loop over movie rental
		Map<Movie, Double> finalPricePerMovie = new LinkedHashMap<>();
        for (Movie movie : rentals.keySet()) {
            double thisAmount = 0;
            // determine amounts for every line
            int price = rentals.get(movie);
            switch (movie.priceCode()) {
                case REGULAR:
                    thisAmount += 2;
                    if (price > 2)
                        thisAmount += (price - 2) * 1.5;
                    break;
                case NEW_RELEASE:
                    thisAmount += price * 3;
                    break;
                case CHILDRENS:
                    thisAmount += 1.5;
                    if (price > 3)
                        thisAmount += (price - 3) * 1.5;
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (movie.priceCode() != null &&
                    (movie.priceCode() == NEW_RELEASE)
                    && price > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
			finalPricePerMovie.put(movie, totalAmount);
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return new Statement(totalAmount, points, movieAndPrice);
    }
}