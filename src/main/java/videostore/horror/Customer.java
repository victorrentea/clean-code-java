package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {
	private final String name;
	private final List<Rental> rental = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rental.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}


	public String statement() {
		return formatHeader()
				+ formatBody()
				+ formatFooter();
	}

	private String formatBody() {
		return rental.stream().map(this::formatStatementLine).collect(joining());
	}

	private String formatHeader() {
		return "Rental Record for " + getName() + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + totalPrice() + "\n"
				+ "You earned " + totalPoints() + " frequent renter points";
	}

	private int totalPoints() {
		return rental.stream().mapToInt(Rental::computeRenterPoints).sum();
	}

	private double totalPrice() {
		return rental.stream().mapToDouble(Rental::computePrice).sum();
	}

	private String formatStatementLine(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
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
