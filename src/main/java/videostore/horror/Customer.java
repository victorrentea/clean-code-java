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

  public void addRental(Movie movie, int daysRented) {
    rentals.add(new Rental(movie, daysRented));
  }

  public String statement() {
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    double totalPrice = 0;
    for (Rental rental : rentals) {
      Movie movie = rental.movie();
      int daysRented = rental.daysRented();
      double price = getPrice(rental);
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

  private double getPrice(Rental rental) {
    double result = 0;
    switch (rental.movie().priceCode()) {
      case REGULAR:
        result += 2;
        if (rental.daysRented() > 2)
          result += (rental.daysRented() - 2) * 1.5;
        break;
      case NEW_RELEASE:
        result += rental.daysRented() * 3;
        break;
      case CHILDREN:
        result += 1.5;
        if (rental.daysRented() > 3)
          result += (rental.daysRented() - 3) * 1.5;
        break;
    }
    return result;
  }
}