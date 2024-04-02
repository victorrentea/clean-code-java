package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Value
class Customer {
    String name;
    List<Rental> rentals = new ArrayList<>(); // preserves order

    public void addRental(Movie movie, int price) {
        rentals.add(new Rental(movie, price));
    }

    public String statement() {
        return getHeader() + getBody() + getFooter();
    }

    private String getHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String getFooter() {
        return "\nAmount owed is " + getTotalPrice() +
                "\nYou earned " + getFrequentRenterPoints() +
                " frequent renter points";
    }

    private String getBody() {
        return rentals.stream()
                .map(Rental::toString)
                .collect(Collectors.joining("\n"));
    }

    private int getFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }

    private double getTotalPrice() {
        return rentals.stream().mapToDouble(Rental::computePrice).sum();
    }
}