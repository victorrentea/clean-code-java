package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    private final String name;
    private final List<Rental> rentals = new LinkedList<>(); // preserves order of elements TODO find a better way to store this

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

    public Statement statement() {
        List<RentalAmount> rentalAmounts = rentals.stream().map(entry -> new RentalAmount(entry.movie(), entry.computeRentalPrice())).toList();
        double totalRentalAmount = rentalAmounts.stream().mapToDouble(RentalAmount::amount).sum();

        int frequentRenterPoints = computeFrequentRenterPoints();
        return new Statement(this, totalRentalAmount, frequentRenterPoints, rentalAmounts);
    }

    private int computeFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (rental.movie().priceCode() == NEW_RELEASE && rental.days() > 1)
                frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

}