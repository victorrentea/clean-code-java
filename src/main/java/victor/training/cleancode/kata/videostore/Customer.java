package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    private final String name;
    private final List<RentalMovie> rentalMovies = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentalMovies.add(new RentalMovie(movie, days));
    }

    public String statement() {
        double totalCost = getTotalCost();
        int frequentRenterPoints = getFrequentRenterPoints();
        return "Rental Record for " + name + "\n" +
                getCostPrints() +
                "Amount owed is " + totalCost + "\nYou earned " + frequentRenterPoints + " frequent renter points";
    }

    private int getFrequentRenterPoints() {
        return rentalMovies.size() +
                (int) rentalMovies.stream()
                        .filter(RentalMovie::shouldAddBonus)
                        .count();
    }

    private String getCostPrints() {
        return rentalMovies.stream()
                .map(rentalMovie ->
                        "\t" + rentalMovie.movie().title() + "\t" + rentalMovie.getRentalCostByPriceCode() + "\n")
                .collect(Collectors.joining());
    }

    private double getTotalCost() {
        return rentalMovies.stream()
                .mapToDouble(RentalMovie::getRentalCostByPriceCode)
                .sum();
    }

}
