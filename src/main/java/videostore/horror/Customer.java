package videostore.horror;

import java.util.ArrayList;
import java.util.List;

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
		for (Rental rental : rentals) {
			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.computeAmount() + "\n";
		}

		result += createFooter(computeTotalAmount(), computeTotalFrequentRenterPoints());
        return result;
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

    private static String createFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent renter points";
    }

}