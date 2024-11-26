package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Customer {
    private final String name;
    private final Map<Movie, RentalDays> rentals = new LinkedHashMap<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int rentalDays) {
        rentals.put(movie, new RentalDays(rentalDays));
    }

    public String statement() {
        String result = "Rental Record for " + name + "\n";
        String moviePrices = createMoviesPricesString();
        double totalPriceToPay = calculateTotalPriceToPay();
        int frequentRenterPoints = calculateFrequentRenterPoints();

        String amountOwned = "Amount owed is " + totalPriceToPay + "\n";
        String frequentRenterPoint = "You earned " + frequentRenterPoints + " frequent renter points";
        return result.concat(moviePrices).concat(amountOwned).concat(frequentRenterPoint);
    }

    private String createMoviesPricesString() {
        return rentals.keySet()
                .stream()
                .map(movie -> "\t" + movie.getTitle() + "\t" +
                        movie.getMovieType()
                                .getStrategy()
                                .calculatePrice(rentals.get(movie).days())
                        + "\n")
                .collect(Collectors.joining());
    }

    private int calculateFrequentRenterPoints() {
        return rentals.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey()
                        .getMovieType()
                        .getStrategy()
                        .calculateFrequentRenterPoints(entry.getValue().days()))
                .sum();
    }

    private double calculateTotalPriceToPay() {
        return rentals.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey()
                        .getMovieType()
                        .getStrategy()
                        .calculatePrice(entry.getValue().days()))
                .sum();
    }

    private record RentalDays(int days) {}
}