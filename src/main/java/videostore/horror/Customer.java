package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public String composeStatement() {
        return composeHeader() + composeBody() + composeFooter();
    }

    private String composeBody() {
        return rentals.stream().map(Customer::composeLine).collect(joining());
    }

    @NotNull
    private String composeFooter() {
        return "Amount owed is " + totalPrice() + "\n" +
               "You earned " + totalPoints() + " frequent renter points";
    }

    private int totalPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }

    private double totalPrice() {
        return rentals.stream().mapToDouble(Rental::getPrice).sum();
    }

    private String composeHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private static String composeLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
    }

    public String getName() {
        return name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }


}