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
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::getBonusPoints).sum();
    }

    private String generateFooter() {
        int frequentRenterPoints = getTotalFrequentRenterPoints();
        double totalPrice = rentals.stream().mapToDouble(Rental::getPrice).sum();
        return "Amount owed is " + totalPrice + "\n" +
               "You earned " + frequentRenterPoints + " frequent renter points";
    }

}