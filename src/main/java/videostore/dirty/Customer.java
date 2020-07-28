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
		return formatHeader() + 
				formatBody() + 
				formatFooter();
	}

	private String formatBody() {
		
		for( Rental r : rentals ) {
			lotsOfLogic(r);
		}
		return rentals.stream().map(this::formatItem).collect(joining());
	}

	private void lotsOfLogic(Rental r) {
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
		System.out.println("a lot of logic " + r);
	}

	private double computeTotalPrice() {
		return rentals.stream().mapToDouble(Rental::computePrice).sum();
	}

	private int computeTotalPoints() {
		return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
	}

	private String formatHeader() {
		return "Rental Record for " + name + "\n";
	}

	private String formatFooter() {
		return "Amount owed is " + computeTotalPrice() + "\n"
				+ "You earned " + computeTotalPoints() + " frequent renter points";
	}

	private String formatItem(Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
	}
}