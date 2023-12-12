package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

class Customer {
    private final String name;
    private final List<Rental> rentals =new LinkedList<>(); // preserves order


    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie m, int d) {
        rentals.add(new Rental(m,d));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        double totalAmount = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        int frequentRenterPoints = rentals.stream()
				.mapToInt(Rental::getRentalPoints)
				.sum();
        for (Rental each : rentals) {
            double thisAmount = each.calculatePrice();
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}