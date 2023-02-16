package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

class Rental {
  private final Movie movie;
  private final int daysRented;

  Rental(Movie movie, int daysRented) {
    this.movie = requireNonNull(movie);
    if (daysRented <= 0) {
      throw new IllegalArgumentException("NO");
    }
    this.daysRented = daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public int getDaysRented() {
    return daysRented;
  }
}

class Customer {
  private final String name;
  private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + name + "\n";
    for (Movie each : rentals.keySet()) {
      int daysRented = rentals.get(each);
      double thisAmount = computeAmountForMovie(each, daysRented);
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (each.getPriceCode() != null &&
          (each.getPriceCode() == PriceCode.NEW_RELEASE)
          && daysRented >= 2)
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