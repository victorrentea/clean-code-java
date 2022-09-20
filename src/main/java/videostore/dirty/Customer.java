package videostore.dirty;

import java.util.*;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints++;
            if (rental.earnsBonus())
                frequentRenterPoints++;

        }
        for (Rental rental : rentals) {
            double thisAmount = getThisAmount(rental);

            // show figures for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += addFooterLines(totalAmount, frequentRenterPoints);
        return result;
    }

    private String addFooterLines(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n" +
               "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private static double getThisAmount(Rental rental) {
        double thisAmount = 0;
        // determine amounts for each line
        switch (rental.getMovie().getCategory()) {
            case REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case CHILDREN:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}