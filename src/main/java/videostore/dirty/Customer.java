package videostore.dirty;

import java.util.*;

import static java.util.stream.Collectors.joining;

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


        // what can go wrong if you call getPrice(rental) TWICE?!!
        // bug#1 the returned value might be different =NOT  Referential Transparent
        // bug#2 it might repeat some side-effect (++, INSERT, sendKafka, 2xPOST)
        // 😱😱😱 PERFORMANCE : SPEED?
        // a PURE Function (extemely fast 99%) won't (1) nor (2)
    public String generateStatement() {
        return generateHeader()
               + generateBody()
               + generateFooter();
    }

    private String generateBody() {
        return rentals.stream().map(this::generateBodyRow).collect(joining());
    }

    private String generateHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String generateBodyRow(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + getPrice(rental) + "\n";
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

    private String generateFooter() {
        int frequentRenterPoints = getTotalFrequentRenterPoints();
        double totalPrice = rentals.stream().mapToDouble(Customer::getPrice).sum();
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