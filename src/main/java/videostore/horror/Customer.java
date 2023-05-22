package videostore.horror;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class Customer {
    private final String name;
    private final List<MovieRental> rentals = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new MovieRental(movie, daysRented));
    }

    public String getName() {
        return name;
    }

    public String statement() { // over-refactoring !
        return formatHeader() + formatBody() + formatFooter();
    }

    private String formatBody() {
        return rentals.stream().map(Customer::formatBodyLine).collect(Collectors.joining());
    }
//    private fun formatBody() = rentals.map{formatBodyLine(it)}.join();

    private int totalPoints() {
        return rentals.stream().mapToInt(MovieRental::calculateRenterPoints).sum();
    }

    private double totalPrice() {
        return rentals.stream().mapToDouble(MovieRental::calculateMoviePrice).sum();
    }

    private static String formatBodyLine(MovieRental rental) {
        return "\t" + rental.movie().title() + "\t" + rental.calculateMoviePrice() + "\n";
    }

    private String formatHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String formatFooter() {
        return "Amount owed is " + totalPrice() + "\n" +
               "You earned " + totalPoints() + " frequent renter points";
    }

}