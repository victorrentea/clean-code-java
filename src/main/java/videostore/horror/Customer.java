package videostore.horror;

import videostore.horror.Movie.PriceCode;

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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + name + "\n";
    for (Rental rental : rentals) {
      Movie movie = rental.getMovie();
      int daysRented = rental.getDaysRented();
      double thisAmount = computeAmountForMovie(movie, daysRented);
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two day new release rental
      if (movie.getPriceCode() != null &&
          (movie.getPriceCode() == PriceCode.NEW_RELEASE)
          && daysRented >= 2)
        frequentRenterPoints++;
      // show figures line for this rental
      result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
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