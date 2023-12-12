package victor.training.cleancode.kata.videostore.horror;

import java.util.*;

class Customer {

	private final String name;

	private final List<Rental>  rentalList = new ArrayList<>();

	public Customer(String name) {
		this.name = Objects.requireNonNull(name);
	}

	public void addRental(Rental rental) {
		rentalList.add(rental);
	}

	public String getName() {
		return name;
	}


	public String getCustomerBillAndRenterPoints(){
	return	Bill.calculateCustomerBillAndRenterPoints(rentalList,name);
	}






}