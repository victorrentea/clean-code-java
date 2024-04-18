package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie rental, int daysRented) {
    rentals.add(new Rental(rental, daysRented));
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalPrice = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + name + "\n";
    for (Rental rental : rentals) {
      frequentRenterPoints += rental.calculateFrequentRenterPoints();

      result += "\t" + rental.movie().title() + "\t" + rental.calculatePrice() + "\n";

      totalPrice += rental.calculatePrice();
    }
    //  IT IS OK TO REPEAT A PURE FUNCTION (no side effects, same result, usually insanely fast)
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }


}