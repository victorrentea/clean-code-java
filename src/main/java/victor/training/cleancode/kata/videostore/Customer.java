package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.*;

class Customer {
    @Getter
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie m, int daysRented) {
        rentals.add(new Rental(m, daysRented));
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // loop over each movie rental
        for (Rental rental : rentals) {
			Movie movie = rental.movie();
            double movieCost = movie.priceCategory().calculateRentalPrice(rental.daysRented());
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two days new release rental
			if (movie.priceCategory() == PriceCategory.NEW_RELEASE && rental.daysRented() > 1) {
				frequentRenterPoints++;
			}
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + movieCost + "\n";
            totalAmount += movieCost;
        }
        for (Rental rental : rentals) {
			Movie movie = rental.movie();
            double movieCost = movie.priceCategory().calculateRentalPrice(rental.daysRented());
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two days new release rental
			if (movie.priceCategory() == PriceCategory.NEW_RELEASE && rental.daysRented() > 1) {
				frequentRenterPoints++;
			}
            // show figures line for this rental
            result += "\t" + movie.title() + "\t" + movieCost + "\n";
            totalAmount += movieCost;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
