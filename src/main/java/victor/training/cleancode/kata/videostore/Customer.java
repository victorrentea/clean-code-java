package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.LinkedHashMap;
import java.util.Map;

@Value
class Customer {
    String name;
    Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public void addRental(Movie movie, int price) {
        rentals.put(movie, price);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Movie each : rentals.keySet()) {
            double thisAmount = 0;
            // determine amounts for every line
            int dr = rentals.get(each);
            switch (each.movieType()) {
                case MovieType.REGULAR:
                    thisAmount += 2;
                    if (dr > 2)
                        thisAmount += (dr - 2) * 1.5;
                    break;
                case MovieType.NEW_RELEASE:
                    thisAmount += dr * 3;
                    break;
                case MovieType.CHILDREN:
                    thisAmount += 1.5;
                    if (dr > 3)
                        thisAmount += (dr - 3) * 1.5;
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (each.movieType() != null &&
                    (each.movieType() == MovieType.NEW_RELEASE)
                    && dr > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + each.title() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}