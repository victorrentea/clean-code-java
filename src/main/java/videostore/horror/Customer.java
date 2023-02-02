package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie movie, int daysRental) {
    rentals.add(new Rental(movie, daysRental));
  }

  public String getName() {
    return name;
  }

  public String generateStatement() {
    return getHeader() + getBody() + getFooter();
  }

  @NotNull
  private String getBody() {
    return rentals.stream().map(Customer::getStatementLine).collect(Collectors.joining());
  }

  @NotNull
  private static String getStatementLine(Rental rental) {
    return "\t" + rental.getMovie().title() + "\t" + rental.getPrice() + "\n";
  }

  @NotNull
  private String getHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  @NotNull
  private String getFooter() {
    return "Amount owed is " + getTotalPrice() + "\nYou earned " + getTotalRenterPoints() + " frequent renter points";
  }

  private double getTotalPrice() {
    return rentals.stream().mapToDouble(Rental::getPrice).sum();
  }

  private int getTotalRenterPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }

}