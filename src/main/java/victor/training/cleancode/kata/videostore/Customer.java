package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
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
    return "Rental Record for " + getName() + "\n" +
           getMovieSummary() +
           // add footer lines
           "Amount owed is " + getTotalRentalFees() + "\n" +
           "You earned " + getFrequentRenterPoints() + " frequent renter points";
  }

  private String getMovieSummary() {
    return rentals.stream()
        .map(rental -> "\t" + rental.movie().title() + "\t" + rental.getMovieRentalFee() + "\n")
        .collect(Collectors.joining());
  }

  private double getTotalRentalFees() {
    return rentals.stream()
        .mapToDouble(Rental::getMovieRentalFee)
        .sum();
  }

  private int getFrequentRenterPoints() {
    return rentals.stream().mapToInt(this::calcPoints).sum();
  }

  private int calcPoints(Rental rental) {
    return rental.movie().priceCode() == PriceCode.NEW_RELEASE && rental.daysRented() > 1 ? 2 : 1;
  }


}
