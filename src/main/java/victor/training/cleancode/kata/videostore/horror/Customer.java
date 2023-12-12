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



	public String calculateBillAndRenterPoints() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentalList) {
			int noDaysRented = rental.getNoDaysRented();

			Movie currentMovie = rental.getMovie();

			double thisAmount = Rental.calculateAmountOfCurrentMovie(currentMovie.getMovieCategory(),noDaysRented);

			frequentRenterPoints += Rental.calculateRenterPoints(currentMovie, noDaysRented);

			// show figures line for this rental
			result += "\t" + currentMovie.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		return getFinalResult(totalAmount,frequentRenterPoints,result);
	}

	private String getFinalResult(double totalAmount,int frequentRenterPoints,String result){
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}





}