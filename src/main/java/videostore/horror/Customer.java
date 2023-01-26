package videostore.horror;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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
    return generateHeader()
            + generateBody()
            + generateFooter();
    // m-am spart in figuri - epic, dar CLAR mult prea mult pt proiectul de zi cu zi.
  }

  private String generateBody() {
    return rentals.stream().map(Customer::generateStatementLine).collect(joining());
  }

  private int computeTotalPoints() {
    return rentals.stream().mapToInt(Rental::computeRenterPoints).sum();
  }

  private double computeTotalPrice() {
    return rentals.stream().mapToDouble(Rental::computePrice).sum();
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

  private  String generateFooter() {
    return "Amount owed is " + computeTotalPrice() + "\n" +
           "You earned " + computeTotalPoints() + " frequent renter points";
  }

}