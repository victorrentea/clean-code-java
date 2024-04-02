package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import lombok.Value;

import java.util.LinkedList;
import java.util.List;

@Value
class Customer {
    @Getter
    String name;
    List<Rental> rentals = new LinkedList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";

        double totalAmount = rentals.stream()
                .mapToDouble(rental -> rental.movie().computePrice(rental.dayRented()))
                .sum();

        //pentru fiecare Rental sa extragem Movie, sa afisam titlu, sa afisam si rentOwnedAmount

        int frequentRenterPoints = rentals.stream()
                .mapToInt(rental -> rental.movie().getFrequentRenterPoints(rental.dayRented()))
                .sum();

        int rentOwnedAmount = 0;

        //result += "\t" + movie.getTitle() + "\t" + rentOwnedAmount + "\n";

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}