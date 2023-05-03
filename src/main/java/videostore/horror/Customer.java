package videostore.horror;

import java.util.ArrayList;
import java.util.List;

import static videostore.horror.MovieCategory.NEW_RELEASE;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental(movie, daysRented));
    }

    public String createStatement() {
        String result = createHeader();
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            double thisAmount = computeAmount(rental);
            frequentRenterPoints += getFrequentRenterPoints(rental);
            // show figures line for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += createFooter(totalAmount, frequentRenterPoints);
        return result;
    }

    private String createHeader() {
        return "Rental Record for " + name + "\n";
    }

    private static String createFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private int getFrequentRenterPoints(Rental rental) {
        boolean isEligibleForBonus = rental.getMovie().getCategory() == NEW_RELEASE && rental.getDaysRented() >= 2;
        return isEligibleForBonus ? 2 : 1;
    }

    private static double computeAmount(Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getCategory()) {
            case REGULAR:
                thisAmount = 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                return thisAmount;
            case NEW_RELEASE:
                return rental.getDaysRented() * 3;
            case CHILDREN:
                thisAmount = 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                return thisAmount;

            default:
                throw new IllegalStateException("Unexpected value: " + rental.getMovie().getCategory());
        }
    }
}