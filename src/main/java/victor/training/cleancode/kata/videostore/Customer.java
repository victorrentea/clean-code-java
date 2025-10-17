package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {

	private final String customerName;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String customerName) {
		this.customerName = customerName;
	};

	public void addRental(Movie movie, int rentalDays) {
		rentals.add(new Rental(movie, rentalDays));
	}

	public String getCustomerName() {
		return customerName;
	}

	public String displayRentals() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getCustomerName() + "\n");
		// loop over each movie rental
		for (Rental rental : rentals) {
			double rentalAmount = rental.computeRentalPrice();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (rental.movie().moviePricingCategory() == MoviePricingCategory.NEW_RELEASE && rental.rentalDays() > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result.append("\t").append(rental.movie().title()).append("\t").append(rentalAmount).append("\n");
			totalAmount += rentalAmount;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

}