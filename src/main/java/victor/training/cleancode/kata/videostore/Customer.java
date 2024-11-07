package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    private static int calculateFrequentRentalPoints(Rental rental) {
        return rental.movie().priceCode() == Movie.PriceCode.NEW_RELEASE && rental.daysRented() > 1 ? 2 : 1;
    }

    public void addRental(Movie m, int d) {
        rentals.add(new Rental(m, d));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        RentalStatement rentalStatement = computeRentalStatement();
        return printRentalStatement(rentalStatement);
    }

    private String printRentalStatement(RentalStatement rentalStatement) {
        String result = "Rental Record for " + getName() + "\n";

        for(RentalStatementItem rentalStatementItem: rentalStatement.rentalStatementItems()){
            // show figures line for this rental
            result += "\t" + rentalStatementItem.rental().movie().title() + "\t" + rentalStatementItem.rentalAmount() + "\n";
        }

        // add footer lines
        result += "Amount owed is " + rentalStatement.totalAmount + "\n";
        result += "You earned " + rentalStatement.frequentRenterPoints + " frequent renter points";
        return result;
    }

    private RentalStatement computeRentalStatement() {
        double totalAmount;
        List<RentalStatementItem> rentalStatementItems = rentals.stream().map(rental -> new RentalStatementItem(rental, rental.getRentalAmount()))
                .collect(Collectors.toList());

        totalAmount = rentals.stream().mapToDouble(Rental::getRentalAmount).sum();

        // add frequent renter points
        int frequentRenterPoints = rentals.stream()
                .mapToInt(Customer::calculateFrequentRentalPoints)
                .sum();


        return new RentalStatement(rentalStatementItems, totalAmount, frequentRenterPoints);
    }

    record RentalStatement(List<RentalStatementItem> rentalStatementItems, Double totalAmount,
                           int frequentRenterPoints) {
    }

    record RentalStatementItem(Rental rental, Double rentalAmount) {
    }

}