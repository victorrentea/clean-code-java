package videostore.horror;

import java.util.*;

import static videostore.horror.Movie.Type.NEW_RELEASE;

class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String generateStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals) {

			frequentRenterPoints += computePoints(rental);

			// apelarea unei functii de doua ori - PUNCTE DE ATENTIE
			// 1) performance daca e scumpa
			// 2) daca face side effects : INSERT
			// 3) daca returneaza alte valori de fiecare data --> tre sa fie "referential transparent"
			// 2+3 = Pure Function

			// show figures line for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";

			totalPrice += rental.computePrice();
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int computePoints(Rental rental) {
		int frequentRenterPoints = 0;
		frequentRenterPoints++;
		if (rental.getMovie().getType() == NEW_RELEASE && rental.getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}

}