package videostore.horror;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return createHeader() + createBody() + createFooter();
    }

	private String createBody() {
		return rentals.stream()
				.map(Customer::createBodyLine)
				.collect(Collectors.joining());
	}

	private static String createBodyLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computeAmount() + "\n";
	}

	private int computeTotalFrequentRenterPoints() {
		return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
	}

	private double computeTotalAmount() {
		return rentals.stream().mapToDouble(Rental::computeAmount).sum();
	}

	private String createHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String createFooter() {
        return "Amount owed is " + computeTotalAmount() + "\n"
                + "You earned " + computeTotalFrequentRenterPoints() + " frequent renter points";
    }

}