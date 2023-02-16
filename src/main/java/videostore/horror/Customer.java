package videostore.horror;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

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
    return formatHeader() + formatBody() + formatFooter();
  }

  private String formatBody() {
    return rentals.stream().map(Customer::formatBodyLine).collect(joining());
  }

  private double getTotalPrice() {
    return rentals.stream().mapToDouble(Rental::computePriceForMovie).sum();
  }

  private int totalPoints() {
    return rentals.stream().mapToInt(Rental::computeRentalPoints).sum();
  }

  private static String formatBodyLine(Rental rental) {
    return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePriceForMovie() + "\n";
  }

  private String formatHeader() {
    return "Rental Record for " + name + "\n";
  }

  private String formatFooter() {
    return "Amount owed is " + getTotalPrice() + "\n"
           + "You earned " + totalPoints() + " frequent renter points";
  }

}