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

    public String generateStatement() {
        double totalPrice = 0;
        String result = generateHeader();

        int frequentRenterPoints = getTotalFrequentRenterPoints();
        for (Rental rental : rentals) {
            double price = getPrice(rental);
            result += generateBodyRow(rental, price);
            totalPrice += price;
        }

        result += generateFooter(totalPrice, frequentRenterPoints);
        return result;
    }

    private String generateHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String generateBodyRow(Rental rental, double price) {
        return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().mapToInt(this::getBonusPoints).sum();
    }

    private int getBonusPoints(Rental rental) {
        int bonus = 1;
        if (rental.earnsBonus()) {
            bonus ++;
        }
        return bonus;
    }

    private String generateFooter(double totalPrice, int frequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n" +
               "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private static double getPrice(Rental rental) {
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