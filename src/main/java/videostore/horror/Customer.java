package videostore.horror;

import java.util.*;

import static java.util.Objects.requireNonNull;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

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
    String result = "Rental Record for " + name + "\n";
    for (Rental rental : rentals) {
      double price = rental.computePriceForMovie();
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (rental.earnsBonus())
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