package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Customer {
  private String name;
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String statement() {
    return formatHeader() + formatBody() + formatFooter();
  }

  private String formatBody() {
    return rentals.stream().map(Customer::formatLine).collect(joining());
  }

  @NotNull
  private String formatHeader() {
    return "Rental Record for " + name + "\n";
  }

  private  String formatFooter() {
    return "Amount owed is " + totalPrice() + "\n"
           + "You earned " + totalPoints() + " frequent renter points";
  }

  private static String formatLine(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.getPrice() + "\n";
  }

  private double totalPrice() {
    return rentals.stream().mapToDouble(Rental::getPrice).sum();
  }

  private int totalPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }

}