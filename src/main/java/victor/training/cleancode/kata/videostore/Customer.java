package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static victor.training.cleancode.kata.videostore.PriceCode.NEW_RELEASE;

class Customer {
    private final String name;
    private final List<RentalMovie> rentalMovies = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    private static double getRentalCostByPriceCode(int rentalDays, PriceCode priceCode) {
        return switch (priceCode) {
            case REGULAR -> calculateRentalCost(rentalDays, 2, 2);
            case NEW_RELEASE -> rentalDays * 3;
            case CHILDREN -> calculateRentalCost(rentalDays, 3, 1.5);
        };
    }

    private static double calculateRentalCost(int rentalDays, int days, double initialAmount) {
        if (rentalDays > days)
            return initialAmount + (rentalDays - days) * 1.5;
        return initialAmount;
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
                        .filter(rentalMovie -> rentalMovie.movie().priceCode() != null &&
                                rentalMovie.movie().priceCode() == NEW_RELEASE &&
                                rentalMovie.days() > 1)
                        .count();
    }

    private String getCostPrints() {
        return rentalMovies.stream()
                .map(rentalMovie ->
                        "\t" + rentalMovie.movie().title() + "\t" + getRentalCostByPriceCode(rentalMovie.days(), rentalMovie.movie().priceCode()) + "\n")
                .collect(Collectors.joining());
    }

    private double getTotalCost() {
        return rentalMovies.stream()
                .mapToDouble(rentalMovie ->
                        getRentalCostByPriceCode(rentalMovie.days(), rentalMovie.movie().priceCode()))
                .sum();
    }

}
