package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie rental, int daysRented) {
        rentals.add(new Rental(rental, daysRented));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        for (Rental rental : rentals) {
            double rentalPrice = calculatePrice(rental);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((rental.movie().priceCode() == PriceCode.NEW_RELEASE)
                    && rental.daysRented() > 1) {
                frequentRenterPoints++;
            }
            // show figures line for this rental
            result += "\t" + rental.movie().title() + "\t" + rentalPrice + "\n";
            totalAmount += rentalPrice;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }


}