package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

class Customer {
  private static final double DEFAULT_RATE = 1.5;
  @Getter
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();
  public Customer(String name) {
    this.name = name;
  }

  private static double calculateRentalPrice(Rental rental, int daysDuration) {
    return switch (rental.movie.type()) {
      case REGULAR -> daysDuration > 2 ? 2 + (daysDuration - 2) * DEFAULT_RATE : 2;
      case NEW_RELEASE -> daysDuration * 3;
      case CHILDREN -> daysDuration > 3 ? 1.5 + (daysDuration - 3) * DEFAULT_RATE : 1.5;
    };
  }

  private static int calculateRenterPoints(Rental rental, int frequentRenterPoints, int daysDuration) {
    // add frequent renter points
    frequentRenterPoints++;
    // add bonus for a two days new release rental
    if ((rental.movie.type() == MovieType.NEW_RELEASE) && daysDuration > 1)
      frequentRenterPoints++;
    return frequentRenterPoints;
  }

  public void addRental(Movie movie, int daysDuration) {
    rentals.add(new Rental(movie, daysDuration));
  }

  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

    // loop over each movie rental
    for (Rental rental : rentals) {
      // determine amounts for every line
      double thisAmount = calculateRentalPrice(rental, rental.daysDuration);
      frequentRenterPoints = calculateRenterPoints(rental, frequentRenterPoints, rental.daysDuration);
      // show figures line for this rental
      result.append("\t").append(rental.movie.title()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }

    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
    return result.toString();
  }

  record Rental(Movie movie, int daysDuration) {
  }

}
