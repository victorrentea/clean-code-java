package videostore.horror;

import java.util.*;
import java.util.Map.Entry;


class Rental {
	private final Movie movie;
	private final int daysRented;
	public Rental(Movie movie, int daysRentals) {
		this.movie = movie;
		this.daysRented = daysRentals;
	}
	public int getDaysRented() {
		return daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	
	public double calculatePrice() {
		double price = 0;
		switch (movie.getType()) {
		case REGULAR:
			price += 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			return price;
		case NEW_RELEASE:
			return daysRented * 3;
		case CHILDRENS:
			price += 1.5;
			if (daysRented > 3)
				price += (daysRented - 3) * 1.5;
			return price;
		default: throw new IllegalArgumentException(getMovie().getType().name());
		}
	}
	public int calculateRenterPoints() {
		// add frequent renter points
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if ((getMovie().getType() == Movie.Type.NEW_RELEASE)
				&& getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}
}

class Customer {
	private String name;
	private Map<Movie, Integer> rentalsMap = new LinkedHashMap<>(); // preserves order
	private List<Rental> rentals = new ArrayList<>();

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
	
	// -1) remove rentalsMap 
	// 0) Body line has 2 params ---> 1 is enough
	// -) calculateRenterPoints() clean
	// 1) break for. extract to mehods?  maybe use streams?
	// 2) why is this in customer
	// 3) SWITCH - extract from it
	// 4) separate the presentation fro mcomputation : MVC

	public String createStatement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = createHeader();
		
		for (Rental rental: rentals) {
			frequentRenterPoints += rental.calculateRenterPoints();
			result += createBodyLine(rental.getMovie(), rental.calculatePrice());
			totalPrice += rental.calculatePrice();
		}
		result += createFooter(totalPrice, frequentRenterPoints);
		return result;
	}

	private String createBodyLine(Movie movie, double price) {
		return "\t" + movie.getTitle() + "\t" + price + "\n";
	}

	private String createHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String createFooter(double totalPrice, int frequentRenterPoints) {
		return "Amount owed is " + totalPrice + "\n" +
				"You earned " + frequentRenterPoints + " frequent renter points";
	}
}