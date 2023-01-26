package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.*;

//// mai DB-friendly
//class CustomerRentals {
//  long customerId;
//  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
//}

class Customer {
  private String name;
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.add(new Rental(movie, daysRented));
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalPrice = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Rental rental : rentals) {
      double price = rental.computePrice();
      frequentRenterPoints += computeRenterPoints(rental);
      // show figures line for this rental
      result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
      totalPrice += price;
    }
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private static int computeRenterPoints(Rental rental) {
    int frequentRenterPoints = 1;
    boolean isNewRelease = rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE;
    if (isNewRelease && rental.getDaysRented() >= 2) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

}