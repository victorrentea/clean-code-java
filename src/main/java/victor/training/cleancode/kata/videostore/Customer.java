package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    private static String bodyLine(Rental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.amount();
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {
        return header() + body() + footer();
    }

    private String body() {
        return rentals.stream().map(Customer::bodyLine).collect(joining("\n"));
    }

    private String header() {
        return "Rental Record for " + getName() + "\n";
    }

    private String footer() {
        return "\nAmount owed is " + getTotalAmount() + "\n"
               + "You earned " + getFrequentRenterPoints() + " frequent renter points";
    }

    private int getFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::renterPoints).sum();
    }

    private double getTotalAmount() {
        return rentals.stream()
            .mapToDouble(Rental::amount)
                .sum();
    }

}
