package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentals.put(Objects.requireNonNull(movie), daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

		for (Movie each : rentals.keySet()) {
			double price ;
			// determine amounts for each line
			int daysRented = rentals.get(each);
			switch (each.getCategory()) {
				case REGULAR:
					price = 2;
					if (daysRented > 2)
						price += (daysRented - 2) * 1.5;
					break;
				case NEW_RELEASE:
					price = daysRented * 3;
					break;
				case CHILDREN:
					price = 1.5;
					if (daysRented > 3)
						price += (daysRented - 3) * 1.5;
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + each.getCategory());
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getCategory() == Movie.Category.NEW_RELEASE) && daysRented >= 2) {
				frequentRenterPoints++;
			}
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t"
						 + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
}