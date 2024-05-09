package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order!

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {
        double price = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Rental rental : rentals) {
            // determine amounts for every line
            double owedAmount = rental.getOwedAmount();
            frequentRenterPoints += rental.getFrequentRenterPoints();
            // show figures line for this rental
            result += "\t" + rental.movie().title() + "\t" + owedAmount + "\n";
            price += owedAmount;
        }
        // add footer lines
        result += "Amount owed is " + price + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }


}