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
    // TODO spargem foru? sa calculam total price si total points separat ?
    for (Rental rental : rentals) {

      double price = rental.computePrice();

      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if ((rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE)
          && rental.getDaysRented() >= 2)
        frequentRenterPoints++;

      // show figures line for this rental
      result += "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
      totalPrice += price;
    }
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

}