package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

@Getter
class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        double totalAmount = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        int frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateFrequentRenterPoints).sum();
        return StatementView.getCustomerSummary(totalAmount, frequentRenterPoints, name, rentals);
    }
}