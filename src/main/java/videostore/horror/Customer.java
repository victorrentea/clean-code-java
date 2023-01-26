package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.LinkedHashMap;
import java.util.Map;

//// mai DB-friendly
//class CustomerRentals {
//  long customerId;
//  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
//}

class Customer {
  private String name;
  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.put(movie, daysRented);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalPrice = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    for (Movie movie : rentals.keySet()) { // TODO spargem foru? sa calculam total price si total points separat ?
      int daysRented = rentals.get(movie);
      double price = computePrice(movie, daysRented);

      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if ((movie.getPriceCode() == PriceCode.NEW_RELEASE)
          && daysRented >= 2)
        frequentRenterPoints++;

      // show figures line for this rental
      result += "\t" + movie.getTitle() + "\t" + price + "\n";
      totalPrice += price;
    }
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private static double computePrice(Movie movie, int daysRented) {
    double price = 0;
    switch (movie.getPriceCode()) {
      case REGULAR:
        price = 2;
        if (daysRented > 2)
          price += (daysRented - 2) * 1.5;
        break;
      case NEW_RELEASE:
        price = daysRented * 3;
        break;
      case CHILDREN:
        price = 1.5;
        if (daysRented > 3)
          price += (daysRented - 3) * 1.5;
        break;
    }
    return price;
  }
}