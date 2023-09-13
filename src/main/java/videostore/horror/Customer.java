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
            thisAmount = calculatePrice(each.priceCode(), daysRental);

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

    private static double calculatePrice(PriceCode priceCode, final int daysRented) {
        switch (priceCode) {
            case REGULAR -> {
                return 2 + ((daysRented > 2) ? (daysRented - 2) * 1.5 : 0);
            }
            case NEW_RELEASE -> {
                return daysRented * 3;
            }
            case CHILDREN -> {
                double price = 1.5;
                if (daysRented > 3)
                    price += (daysRented - 3) * 1.5;
                return price;
            }
            default -> throw new IllegalStateException("Unexpected value: " + priceCode);
        }
    }
}