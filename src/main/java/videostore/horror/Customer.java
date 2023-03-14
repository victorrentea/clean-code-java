package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.*;

class Customer {
    private final String name;
    private final List<Rental> rentalList = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public String composeStatement() {
        String statement = composeHeader();

        int frequentRenterPoints = rentalList.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
        for (Rental rental : rentalList) {
            statement += composeLine(rental);
        }
        double totalPrice = rentalList.stream().mapToDouble(Rental::getPrice).sum();
        statement += composeFooter(totalPrice, frequentRenterPoints);
        return statement;
    }

    @NotNull
    private String composeHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    @NotNull
    private static String composeFooter(double totalPrice, int frequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n" + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    @NotNull
    private static String composeLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
    }

    public String getName() {
        return name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentalList.add(new Rental(movie, daysRented));
    }


}