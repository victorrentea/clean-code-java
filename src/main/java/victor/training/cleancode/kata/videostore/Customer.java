package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>(); // preserves order of elements

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
        String result = "Rental Record for " + getName() + "\n";


        RentalStatement rentalStatement = computeRentalStatement();

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

        Double totalAmount = 0.0;
        List<RentalStatementItem> rentalStatementItems = new ArrayList<>();
        for (Rental rental : rentals) {
            double thisAmount =
                    rental.getRentalAmount();

            RentalStatementItem rentalStatementItem =
                    new RentalStatementItem(rental, thisAmount);

            rentalStatementItems.add(rentalStatementItem);
        }

        for (Rental rental : rentals) {
            double thisAmount =
                    rental.getRentalAmount();

            totalAmount += thisAmount;
        }

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