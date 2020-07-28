package videostore.dirty;
import static java.util.stream.Collectors.joining;

import java.util.*;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental arg) {
		rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	// FOr Andres: 
	
	// parallelStream is BAD
	// 1) BAD: if you have maaaany elements for each you do a verry 
	// little processing -> the overhead of calling lambdas, 
	// >>>> the benefit of paralelizing a string concatention 
	
	// 2) OK if you have few elements for each you do A LOT of PROCESSING
	
	// problems parallelStream: that by default in runs on a 
	// SHARED THREAD POOL among the entire JVM Common ForkJoinPool
	
	// ---> you are NOT allowed to do any I/O (SELECT, REST, FIles)
	// while running on the Common Pool (7 thread)
	
	// 2) continued: but without doing any I/O -> 99.9% 
	// ALL cases in our app that take time involve a DB/REST/FILE
	// unless crypto, digsing, 
	
	public String formatStatement() {
		String result = formatHeader();
		result += formatBody();
				
		result += formatFooter();
		return result;
	}

	private String formatBody() {
		return rentals.stream().map(this::formatItem).collect(joining());
	}

	private double computeTotalPrice() {
		double totalPrice = 0;
		for (Rental rental:rentals) {
			totalPrice += rental.computeAmount();
		}
		return totalPrice;
	}

	private int computeTotalPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental:rentals) {
			frequentRenterPoints += rental.computeRenterPoints();
		}
		return frequentRenterPoints;
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				+ "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private String formatItem(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computeAmount() + "\n";
	}
}