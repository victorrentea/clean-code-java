package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie m, int daysRented) {
        rentals.add(new Rental(m, daysRented));
    }

    public String statement() {
        double totalAmount = rentals.stream()
                .mapToDouble(movie -> movie.movie().priceCategory().calculateRentalPrice(movie.daysRented()))
                .sum();

        int frequentRenterPoints = rentals.stream()
                .mapToInt(movie -> {
                    int points = 1;
                    if (movie.movie().priceCategory() == PriceCategory.NEW_RELEASE && movie.daysRented() > 1) {
                        points++;
                    }
                    return points;
                })
                .sum();

        String result = "Rental Record for " + getName() + "\n";
        String rentalLines = rentals.stream()
                .map(rental -> "\t" + rental.movie().title() + "\t" + rental.movie().priceCategory().calculateRentalPrice(rental.daysRented()))
                .collect(Collectors.joining("\n"));

        result += rentalLines + "\n";
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
