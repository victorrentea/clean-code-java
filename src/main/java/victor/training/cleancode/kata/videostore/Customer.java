package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new LinkedList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }

    public String statement() {
        double totalAmount = rentals.stream().mapToDouble(Rental::getPrice).sum();

        String result = "Rental Record for %s\n".formatted(getName());

        result += (rentals.stream().map(rental -> "\t%s\t%s\n".formatted(rental.movie().title(), rental.getPrice())).collect(Collectors.joining()));

        int frequentRenterPoints = rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();

        // add footer lines

        result += "Amount owed is %s\n".formatted(totalAmount);
        result += "You earned %d frequent renter points".formatted(frequentRenterPoints);
        return result;
    }

}
