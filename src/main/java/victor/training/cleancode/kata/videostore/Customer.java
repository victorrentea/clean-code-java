package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Customer {
    private final String name;
    private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements
    private int frequentRenterPoints;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movieToRent, int daysRented) {
        frequentRenterPoints += movieToRent.calcRenterPoints(daysRented);
        rentals.put(movieToRent, daysRented);
    }

    public String generateRentalStatement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        double totalOwedAmount = rentals.keySet().stream()
                .mapToDouble(movie -> movie.calcRentalCost(rentals.get(movie))).sum();

        rentals.keySet().stream().map(this::generateRentalLine).forEach(result::append);

        return addFooterLines(result, totalOwedAmount).toString();
    }

    private String generateRentalLine(Movie movie) {
        return "\t" + movie.title() + "\t" + movie.calcRentalCost(rentals.get(movie)) + "\n";
    }

    private StringBuilder addFooterLines(StringBuilder result, double totalOwedAmount) {
        return result.append("Amount owed is ").append(totalOwedAmount).append("\n").append("You earned ")
                .append(frequentRenterPoints).append(" frequent renter points");
    }
}