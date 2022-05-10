package videostore.horror;

import java.util.*;

class Customer {
	private final String name;
	private final List<Rental> rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentalList.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints;
		String result = "Rental Record for " + getName() + "\n";

		frequentRenterPoints = rentalList.stream().mapToInt(Rental::computeRenterPoints).sum();

		for (Rental rental : rentalList) {
			// ce probleme poti avea cand repeti un apel de functie ?
			// 1 daca modifica state (++, insert, POST, tibco send)\
			// 2 nu timp, nu random, nu READ pe network
			// - performanta daca dureaza timp/sau aloca multa mem

			// PURE FUNCTION = nu side effects (setteri) + da acelasi rezultat pt acelasi input

			result += formatStatementLine(rental, rental.computePrice());
			totalPrice += rental.computePrice();
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}
// "viata-i greu, nu toti o poate" - din batrani

	private String formatStatementLine(Rental rental, double price) {
		return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
	}

}
//@Test // buna idee
//void test() {
//	for (Movie.Category value : Movie.Category.values()) {
//		computePrice(new Movie()) sa nu arunce illegalStateEx
//	}
//}

		//return switch (movie.getCategory()) { // nu ai nimic in afara de switch in functie
		//			case REGULAR -> computeRegularPrice(daysRented); //1 line / case
		//			case NEW_RELEASE -> computeNewReleasePrice(daysRented);
		//			case CHILDREN -> computeChildrenPrice(daysRented);
		//			// ai default cu throw
		//		};
