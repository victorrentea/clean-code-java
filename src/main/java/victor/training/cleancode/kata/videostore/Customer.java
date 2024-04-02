package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class Customer {
    String name;
    List<Rental> rentals = new ArrayList<>(); // preserves order

    public void addRental(Movie movie, int price) {
        rentals.add(new Rental(movie, price));
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
//        totalAmount = rentals.stream().mapToDouble(Rental::computePrice).sum();
          for (Rental rental : rentals) {
            double thisAmount = 0;
            // determine amounts for every line
            thisAmount = rental.computePrice();
            result += "\t" + rental.movie().title() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        for (Rental rental : rentals) {
            // add frequent renter points
//            result += "\t" + rental.movie().title() + "\t" + thisAmount + "\n";
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (rental.movie().movieType() == MovieType.NEW_RELEASE && rental.rentDays() > 1)
                frequentRenterPoints++;
            // show figures line for this rental

        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}