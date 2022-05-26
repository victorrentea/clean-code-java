package videostore.horror;


import java.util.ArrayList;
import java.util.List;

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
        double totalPrice;
        int frequentRenterPoints;
        String result = formatHeader();

        totalPrice = rentals.stream().mapToDouble(Rental::calculatePrice).sum();
        //1) daca face un INSERT
        //2) daca nu intoarce acelasi lucru cand o chemi a doua oara (eg se bazeaza pe timp sau random)
        // Daca o functie nu face nici 1 nici 2: ==> functia e PURE FUNCTION
        frequentRenterPoints = rentals.stream().mapToInt(Rental::calculateRenterPoints).sum();

        for (Rental rental : rentals) {
            // show figures line for this rental
            result += formatRentalLine(rental);
        }

        result += formatFooter(totalPrice, frequentRenterPoints);
        return result;
    }

    private String formatHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String formatFooter(double totalPrice, int frequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private String formatRentalLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" +
                rental.calculatePrice() + "\n";
    }

}