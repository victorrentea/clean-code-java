package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Value
class Customer {
    String name;
    List<Rental> rentals = new ArrayList<>();

    public void addRental(final Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        return getHeader() + getBody() + getFooter();
    }

    private String getHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String getFooter() {
        return "\nAmount owed is " + getTotalPrice() +
                "\nYou earned " + getFrequentRenterPoints() +
                " frequent renter points";
    }

    private String getBody() {
        return rentals.stream()
            .map(Rental::toBodyLine)
            .collect(joining("\n"));
    }

    private int getFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
    }

    private double getTotalPrice() {
        return rentals.stream().mapToDouble(Rental::computePrice).sum();
    }
}