package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return formatHeader() + formatBody() + formatFooter();
    }

    private String formatHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String formatBody() {
        return rentals.stream().map(Customer::formatBodyLine).collect(joining());
    }

    private static String formatBodyLine(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";
    }

    private String formatFooter() {
        return "Amount owed is " + totalPrice() + "\n" +
            "You earned " + totalPoints() + " frequent renter points";
    }

    private int totalPoints() {
        return rentals.stream().mapToInt(Rental::calculatePoints).sum();
    }

    private double totalPrice() {
        return rentals.stream().mapToDouble(Rental::calculatePrice).sum();
    }

}