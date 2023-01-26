package videostore.horror;

import videostore.horror.Movie.PriceCode;

import java.util.*;

//// mai DB-friendly
//class CustomerRentals {
//  long customerId;
//  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
//}

class Rental {
  private final Movie movie;
  private final int daysRented;

  Rental(Movie movie, int daysRented) {
    this.movie = Objects.requireNonNull(movie);
    this.daysRented = daysRented;
  }
  public int getDaysRented() {
    return daysRented;
  }
  public Movie getMovie() {
    return movie;
  }
}

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

      double price = computePrice(rental);

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

  // Feature Envy code smell: metoda care tpt ce face e sa extraga date din param
  private static double computePrice(Rental rental) {
    double price = 0;
    switch (rental.getMovie().getPriceCode()) {
      case REGULAR:
        price = 2;
        if (rental.getDaysRented() > 2)
          price += (rental.getDaysRented() - 2) * 1.5;
        break;
      case NEW_RELEASE:
        price = rental.getDaysRented() * 3;
        break;
      case CHILDREN:
        price = 1.5;
        if (rental.getDaysRented() > 3)
          price += (rental.getDaysRented() - 3) * 1.5;
        break;
    }
    return price;
  }
}