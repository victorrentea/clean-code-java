package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Customer {
  // life is not sweet w/o Lombok in FinTech
  private final String customerName;

  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String customerName) {
    this.customerName = customerName;
  }

  public void addRental(Movie movie, int rentalDays) {
    rentals.add(new Rental(movie, rentalDays));
  }

  public String displayRentals() {
    return header() + body() + footer();
  }

  private String header() {
    return "Rental Record for " + customerName + "\n";
  }

  private String body() {
    return rentals.stream().map(this::renderRentalStatement).collect(joining("\n")) + "\n";
  }

  private String footer() {
    return "Amount owed is " + getTotalAmount() + "\n" +
           "You earned " + getFrequentRenterPoints() + " frequent renter points";
  }

  private String renderRentalStatement(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.computeRentalPrice();
  }

  private double getTotalAmount() {
    return rentals.stream().mapToDouble(Rental::computeRentalPrice).sum();
  }

  private int getFrequentRenterPoints() {
    // add frequent points bonus for a two day new release rental
    int newReleaseExtraPoints = (int) rentals.stream().filter(Rental::eligibleForBonus).count();
    int frequentRenterPoints = rentals.size() + newReleaseExtraPoints;
    return frequentRenterPoints;
  }

}