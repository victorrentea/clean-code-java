package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
  private final String name;
  private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie m, int d) {
    rentals.put(m, d);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + name + "\n";
    for (Movie each : rentals.keySet()) {
      int dr = rentals.get(each);
      double thisAmount = computeAmountForMovie(each, dr);
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (each.getPriceCode() != null &&
          (each.getPriceCode() == PriceCode.NEW_RELEASE)
          && dr >= 2)
        frequentRenterPoints++;
      // show figures line for this rental
      result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private static double computeAmountForMovie(Movie each, int dr) {
    double thisAmount = 0;
    switch (each.getPriceCode()) {
      case REGULAR:
        thisAmount = 2;
        if (dr > 2)
          thisAmount += (dr - 2) * 1.5;
        break;
      case NEW_RELEASE:
        thisAmount = dr * 3;
        break;
      case CHILDREN:
        thisAmount = 1.5;
        if (dr > 3)
          thisAmount += (dr - 3) * 1.5;
        break;
    }
    return thisAmount;
  }
}