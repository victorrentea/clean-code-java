package videostore.horror;

import java.util.*;

import static videostore.horror.PriceCode.NEW_RELEASE;

class Customer {
    private final String name;
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    ;

    public void addRental(Movie m, int d) {
        rentals.put(m, d);
    }

    public String getName() {
        return name;
    }

    // test
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Movie each : rentals.keySet()) {
            double thisAmount = 0;
            // determine amounts for every line
            int daysRental = rentals.get(each);
            thisAmount = calculatePrice(each, daysRental);

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.priceCode() == NEW_RELEASE) && daysRental > 1)
                frequentRenterPoints++;

            // show figures line for this rental
            result += "\t" + each.title() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static double calculatePrice(final Movie each, final int dr) {
        double thisAmount = 0;
        switch (each.priceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (dr > 2)
                    thisAmount += (dr - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += dr * 3;
                break;
            case CHILDREN:
                thisAmount += 1.5;
                if (dr > 3)
                    thisAmount += (dr - 3) * 1.5;
                break;
                // todo - default
        }
        return thisAmount;
    }
}