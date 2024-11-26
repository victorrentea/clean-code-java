package victor.training.cleancode.kata.videostore;

import java.util.*;
import java.util.stream.Collectors;

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

  public String buildInvoice() {
    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

    double totalRentalFees = rentals.stream()
            .mapToDouble(Rental::getMovieRentalFee)
            .sum();

    String movieSummary = rentals.stream()
            .map(rental -> "\t" + rental.movie().title() + "\t" + rental.getMovieRentalFee() + "\n")
            .collect(Collectors.joining());

    result.append(movieSummary);

    // add footer lines
    result.append("Amount owed is ").append(totalRentalFees).append("\n");
    result.append("You earned ").append(getFrequentRenterPoints()).append(" frequent renter points");
    return result.toString();
  }

  private int getFrequentRenterPoints() {
    return rentals.stream().mapToInt(this::calcPoints).sum();
  }

  private int calcPoints(Rental rental) {
    return rental.movie().priceCode() == PriceCode.NEW_RELEASE && rental.daysRented() > 1 ? 2 : 1;
  }


}
