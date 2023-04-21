package videostore.horror;

import java.util.ArrayList;
import java.util.List;

class Customer {
  private String name;
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String statement() {
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    double totalPrice = 0;
    for (Rental rental : rentals) {
      double price = rental.getPrice();
      frequentRenterPoints += rental.getFrequentRenterPoints();
      // show figures line for this rental
      result += "\t" + rental.movie().title() + "\t" + price + "\n";
      totalPrice += price;
    }
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

}