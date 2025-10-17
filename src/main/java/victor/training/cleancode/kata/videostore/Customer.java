package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {

	@Getter
    private final String customerName;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String customerName) {
		this.customerName = customerName;
	}

	public void addRental(Movie movie, int rentalDays) {
		rentals.add(new Rental(movie, rentalDays));
	}

    public String displayRentals() {
		String result ="Rental Record for " + getCustomerName() + "\n";
		// loop over each movie rental
		result += rentals.stream().map(this::renderRentalStatement).collect(Collectors.joining());
		double totalAmount = rentals.stream().mapToDouble(Rental::computeRentalPrice).sum();

		// add frequent points bonus for a two day new release rental
		int newReleaseExtraPoints = (int) rentals.stream().filter(Customer::eligibleForBonus).count();
		int frequentRenterPoints = rentals.size() + newReleaseExtraPoints;

		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " new release renter points";
		return result;
	}

	private static boolean eligibleForBonus(Rental rental) {
		return rental.movie().moviePricingCategory() == MoviePricingCategory.NEW_RELEASE && rental.rentalDays() > 1;
	}

	private String renderRentalStatement(Rental rental) {
		return "\t" + rental.movie().title() + "\t" + rental.computeRentalPrice() + "\n";
	}
}