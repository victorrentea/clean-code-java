package videostore.horror;

import org.jetbrains.annotations.NotNull;

import java.util.*;

//// mai DB-friendly
//class CustomerRentals {
//  long customerId;
//  private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
//}

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

  public String generateStatement() {
    String result = generateHeader();

    int frequentRenterPoints = rentals.stream().mapToInt(Rental::computeRenterPoints).sum();

    double totalPrice = 0;
    for (Rental rental : rentals) {
      totalPrice += rental.computePrice();
    }
    for (Rental rental : rentals) {
      result += generateStatementLine(rental);
    }

    result += generateFooter(totalPrice, frequentRenterPoints);
    return result;
  }

  // ce are functia asta special de n-o s-o mutam in Rental?
  // 1) incarca prea mult Rental cu logica ffff specifica doar mie. !
  // nu este reusable domain logic, ci un detaliu marunt al usecaseului meu
  // 2) Model (domain) <> VC (presentation)
  // 3) SRP sa tii intreaga gemerare de statement aici , nu s-o imprastii
  private static String generateStatementLine(Rental rental) {
    return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
  }

  private String generateHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  private static String generateFooter(double totalPrice, int frequentRenterPoints) {
    return "Amount owed is " + totalPrice + "\n" +
           "You earned " + frequentRenterPoints + " frequent renter points";
  }

}