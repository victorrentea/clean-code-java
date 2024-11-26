package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.movie.RentedMovie;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  private final String name;
  private final List<RentedMovie> rentals = new LinkedList<>(); // preserves order of elements

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(RentedMovie arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  public String getClientRentalMoviesPrintableStatus() {
    // optimize code for browsing.
    return formatHeader() + formatBody() + formatFooter();
  }

  private int totalPoints() {
    return rentals.stream().map(RentedMovie::getFrequentRenterPoints).mapToInt(Integer::intValue).sum();
  }

  private double totalAmount() {
    return rentals.stream().mapToDouble(RentedMovie::getPrice).sum();
  }

  private String formatHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  private String formatBody() {
    return rentals.stream().map(RentedMovie::getPrintableTitleAndPrice).collect(Collectors.joining("\n"));
  }

  private String formatFooter() {
    return "\nAmount owed is " + totalAmount() + "\n"
           + "You earned " + totalPoints() + " frequent renter points";
  }


}