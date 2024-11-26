package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    private final String name;
    private final List<MovieRental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int rentalDays) {
        rentals.add(new MovieRental(movie, rentalDays));
    }

    public String movieRentalStatus() {
        String result = "Rental Record for " + name + "\n";
        String moviePrices = createMoviesPricesString();
        double totalPriceToPay = calculateTotalPriceToPay();
        int frequentRenterPoints = calculateFrequentRenterPoints();

        String amountOwned = "Amount owed is " + totalPriceToPay + "\n";
        String frequentRenterPoint = "You earned " + frequentRenterPoints + " frequent renter points";
        return result.concat(moviePrices).concat(amountOwned).concat(frequentRenterPoint);
    }

    private String createMoviesPricesString() {
        return rentals
                .stream()
                .map(movie -> "\t" + movie.getTitle() + "\t" +
                        movie.getMovieType()
                                .getStrategy()
                                .calculatePrice(movieRental.rentalDays())
                        + "\n")
                .collect(Collectors.joining());
    }

    private int calculateFrequentRenterPoints() {
        return rentals
                .stream()
                .mapToInt(entry -> entry.getKey()
                        .getMovieType()
                        .getStrategy()
                        .calculateFrequentRenterPoints(entry.getValue().days()))
                .sum();
    }

    private double calculateTotalPriceToPay() {
        return rentals
                .stream()
                .mapToDouble(entry -> entry.getKey()
                        .getMovieType()
                        .getStrategy()
                        .calculatePrice(entry.getValue().days()))
                .sum();
    }
}