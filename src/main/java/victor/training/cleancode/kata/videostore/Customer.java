package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

// test push

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental newRental) {
        rentals.add(newRental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        int frequentRenterPoints = rentals.stream().mapToInt(Customer::calculatePoints).sum();

        String result = rentals.stream()
                .map(rental -> "\t" + rental.movie().title() + "\t" + String.format("%.1f", rental.calculatePrice()) + "\n")
                .collect(Collectors.joining());

        return "Rental Record for " + getName() + "\n" + result +
                "Amount owed is " + String.format("%.1f", totalAmount) + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points";

    }

    private static int calculatePoints(Rental rental) {
        int daysRented = rental.daysRented();
        int points = 1;
        if ((rental.movie().priceCode() == PriceCode.NEW_RELEASE) && daysRented > 1) {
            points++;
        }
        return points;
    }
}
