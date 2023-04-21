package videostore.horror;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
  private String name;
  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

  public Customer(String name) {
    this.name = name;
  }

  private static double getPrice(Movie movie, int daysRented) {
    double result = 0;
    switch (movie.priceCode()) {
      case REGULAR:
        result += 2;
        if (daysRented > 2)
          result += (daysRented - 2) * 1.5;
        break;
      case NEW_RELEASE:
        result += daysRented * 3;
        break;
      case CHILDREN:
        result += 1.5;
        if (daysRented > 3)
          result += (daysRented - 3) * 1.5;
        break;
    }
    return result;
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.put(movie, daysRented);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    double totalPrice = 0;
    for (Movie movie : rentals.keySet()) {
      // determine amounts for each line
      int daysRented = rentals.get(movie);
      double price = getPrice(movie, daysRented);
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (movie.priceCode() != null &&
          (movie.priceCode() == MovieType.NEW_RELEASE)
          && daysRented >= 2)
        frequentRenterPoints++;
      // show figures line for this rental
      result += "\t" + movie.title() + "\t" + price + "\n";
      totalPrice += price;
    }
    // add footer lines
    result += "Amount owed is " + totalPrice + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }
}