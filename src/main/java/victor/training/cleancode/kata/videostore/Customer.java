package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";

        result += rentals.stream()
                .map(r -> "\t" + r.movie().title() + "\t" + r.getOwedAmount() + "\n")
                .collect(Collectors.joining(""));

        int frequentRenterPoints = rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
        double price = rentals.stream().mapToDouble(Rental::getOwedAmount).sum();
        result += "Amount owed is " + price + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}