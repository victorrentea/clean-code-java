package videostore.horror;

import java.util.*;

class Rental {
	private final Movie movie;
	private final int daysRented;

	Rental(Movie movie, int daysRented) {
		this.movie = Objects.requireNonNull(movie);
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int computeFrequentRenterPoints() {
		int points = 1;
		boolean isNewRelease = getMovie().getCategory() == MovieCategory.NEW_RELEASE;
		if (isNewRelease && getDaysRented() >= 2) {
			points++;
		}
		return points;
	}

	public double computePrice() {
		double price = 0;
		switch (getMovie().getCategory()) {
			case REGULAR:
				price += 2;
				if (getDaysRented() > 2)
					price += (getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				price += getDaysRented() * 3;
				break;
			case CHILDRENS:
				price += 1.5;
				if (getDaysRented() > 3)
					price += (getDaysRented() - 3) * 1.5;
				break;
		}
		return price;
	}
}

class Customer {
	private final String name;
	// Map.get(key) // +O(1)
	private final Map<Movie, Integer> rentalsMap = new LinkedHashMap<>(); // preserves order
	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int daysRented) {
		rentalsMap.put(movie, daysRented);
		rentals.add(new Rental(movie, daysRented));
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0; // code smells: acumulatori
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentals) {
			Movie movie = rental.getMovie();

			double price = rental.computePrice();
			frequentRenterPoints += rental.computeFrequentRenterPoints();

			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + price + "\n";
		}
		for (Rental rental : rentals) {
			double price = rental.computePrice();
			// pentru a putea sparge forul in 2, va trebui sa repet un apel de functie (rental.computePrice()).
			// cand e asta riscant?
			// 1) daca functia nu e pura => buguri
				// PURE =
				// 	a) da acelasi rezultat daca e chemata de mai mute ori cu acelasi input
				// 	b) nu modifica starea din jur (nu are side effect)
			// 2) durata de executie sa fie mica. (in 99% din cazuri o fct pura e super fast).
			// pt ca de obicei intarzierile sunt cauzate de DB, alte APIuri, WS...
				// evaluateChessBoard(board) sau validate dig signature (message)

			// Concluzie. Noi iubim fct pure pt ca sunt safe de chemat de cate ori avem nevoie!

			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

}