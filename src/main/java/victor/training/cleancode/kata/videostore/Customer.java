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
        int frequentRenterPoints = rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();

        return """
        Rental Record for %s
        %s
        Amount owed is %s
        You earned %d frequent renter points""".formatted(
                getName(),
                rentals.stream().map(Rental::toString).collect(Collectors.joining("\n")),
                totalAmount,
                frequentRenterPoints
        );
    }

}
