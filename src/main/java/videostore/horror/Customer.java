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
      // bis
      result += generateStatementLine(rental, rental.computePrice());
    }

    // am repetat un apel de functie (computePrice). Cand e asta o idee proasta (in general)
      // == cand nu E PURE
    // #1 da alt rezultat a doua oara (la bis) de ce ? ca a adus de pe retea
    // #2 daca modifica chestii : INSERT, MQ.send, event.fire
    // obs: daca functia e PURA dar tine ff mult timp (eg> generam grafice, parsama XML din string)

    result += generateFooter(totalPrice, frequentRenterPoints);
    return result;
  }

  private static String generateStatementLine(Rental rental, double price) {
    return "\t" + rental.getMovie().getTitle() + "\t" + price + "\n";
  }

  @NotNull
  private String generateHeader() {
    return "Rental Record for " + getName() + "\n";
  }

  @NotNull
  private static String generateFooter(double totalPrice, int frequentRenterPoints) {
    return "Amount owed is " + totalPrice + "\n" +
           "You earned " + frequentRenterPoints + " frequent renter points";
  }

}