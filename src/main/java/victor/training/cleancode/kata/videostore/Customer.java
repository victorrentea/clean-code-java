package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import lombok.Value;

import java.util.LinkedHashMap;
import java.util.Map;

@Value
class Customer {
    @Getter
    String name;
    Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }


    public void addRental(Movie m, int d) {
        rentals.put(m, d);
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        double totalAmount = 0;
        for (Movie movie : rentals.keySet()) {
            // determine amounts for every line
            int daysRented = rentals.get(movie);
            double rentOwnedAmount = movie.computePrice(daysRented);
            // show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + rentOwnedAmount + "\n";
            totalAmount += rentOwnedAmount;
        }

        int frequentRenterPoints = rentals.keySet().stream()
                .mapToInt(movie -> movie.getFrequentRenterPoints(rentals.get(movie)))
                .sum();

        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}