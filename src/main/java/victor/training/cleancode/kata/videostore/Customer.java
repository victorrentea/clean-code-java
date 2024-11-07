package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
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
        double totalAmount = getTotalAmount();
        int frequentRenterPoints = getFrequentRenterPoints();
        return getRentalRecords(totalAmount, frequentRenterPoints);
    }

    private String getRentalRecords(final double totalAmount, final int frequentRenterPoints) {
        String rentalLines = rentals.stream()
                .map(rental -> "\t" + rental.movie().title() + "\t" + rental.movie().priceCategory().calculateRentalPrice(rental.daysRented()))
                .collect(Collectors.joining("\n"));

        // add footer lines
        return "Rental Record for " + getName() + "\n" + (rentalLines + "\n") + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points");
    }

    private int getFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(movie -> {
                    int points = 1;
                    if (movie.movie().priceCategory() == PriceCategory.NEW_RELEASE && movie.daysRented() > 1) {
                        points++;
                    }
                    return points;
                })
                .sum();
    }

    private double getTotalAmount() {
        return rentals.stream()
                .mapToDouble(movie -> movie.movie().priceCategory().calculateRentalPrice(movie.daysRented()))
                .sum();
    }
}
