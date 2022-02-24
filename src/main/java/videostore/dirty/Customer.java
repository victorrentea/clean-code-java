package videostore.dirty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

class Customer {
   private final String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = Objects.requireNonNull(name);
   }

   public void addRental(Rental rental) {
      rentals.add(rental);
   }

   public String getName() {
      return name;
   }

   public String statement() {
      return formatHeader()
             + formatBody()
             + formatFooter();
   }

   private String formatBody() {
      return rentals.stream().map(this::formatLine).collect(joining());
   }

   private String formatHeader() {
      return "Rental Record for " + getName() + "\n";
   }

   private String formatLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.computePrice() + "\n";
   }

   private String formatFooter() {
      return "Amount owed is " + computeTotalPrice() + "\n"
             + "You earned " + computetTotalRenterPoints() + " frequent renter points";
   }

   private double computeTotalPrice() {
      double totalPrice = 0;
      for (Rental rental : rentals) {
         totalPrice += rental.computePrice();
      }
      return totalPrice;
   }

   private int computetTotalRenterPoints() {
      int frequentRenterPoints = 0;
      for (Rental rental : rentals) {
         frequentRenterPoints += rental.computeFrequentRenterPoints();
      }
      return frequentRenterPoints;
   }

   // POTI REPETA UN APEL DE FUNCTIE DACA:
   // 1 SIDE EFFECTS = BUGURI (repeti scrierea)
   // 2 da acelasi rezultat pentru parametrii = REFERENTIAL TRANSPARENT ~= idempotent
   // 3 PERFORMANTA
   // ==  PURE(1+2) & FAST

}