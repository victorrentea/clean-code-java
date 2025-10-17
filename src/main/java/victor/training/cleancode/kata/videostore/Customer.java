package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
	private final String name;
	private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements TODO find a better way to store this

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie,daysRented));
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
        int rewardPoints = 0;
		StringBuilder statement = new StringBuilder("Rental Record for " + getName() + "\n");
        double totalCost = rentals.stream().mapToDouble(Rental::getCost).sum();

        for (Rental rental : rentals) {
            double rentalCost = rental.getCost();
			rewardPoints++;
			// add bonus for a two day new release rental
			if (rental.movie().getPriceCode() == PriceCode.NEW_RELEASE && rental.daysRented() > 1)
				rewardPoints++;
			// show figures line for this rental
			statement.append("\t").append(rental.movie().getTitle()).append("\t").append(rentalCost).append("\n");
		}
		// add footer lines
		statement.append("Amount owed is ").append(totalCost).append("\n");
		statement.append("You earned ").append(rewardPoints).append(" frequent renter points");
		return statement.toString();
	}


}