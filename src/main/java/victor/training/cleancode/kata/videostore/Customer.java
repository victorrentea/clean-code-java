package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String statement() {
        int frequentRenterPoints = 0;
        StringBuilder rentalStatement = new StringBuilder("Rental Record for " + name + "\n");

        for (Rental rentedMovie : rentals) {
            frequentRenterPoints += rentedMovie.computeFrequentRenterPoints();

            rentalStatement.append("\t").append(rentedMovie.movie().title()).append("\t").append(rentedMovie.getRentalPrice()).append("\n");
        }
        double totalAmount = rentals.stream().mapToDouble(Rental::getRentalPrice).sum();

        rentalStatement.append("Amount owed is ").append(totalAmount).append("\n");
        rentalStatement.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return rentalStatement.toString();
    }
}