package videostore.horror;


import java.util.ArrayList;
import java.util.List;

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

    public String generateStatement() {
        return formatHeader()
                + formatBody()
                + formatFooter();
    }

    private String formatBody() {
        return rentals.stream().map(this::formatRentalLine).collect(joining());
    }

    private String formatHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String formatFooter() {
        double totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        int totalPoints = rentals.stream().mapToInt(Rental::calculateRenterPoints).sum();
        return "Amount owed is " + totalPrice + "\n" +
                "You earned " + totalPoints + " frequent renter points";
    }

    private String formatRentalLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" +
                rental.calculatePrice() + "\n";
    }

}