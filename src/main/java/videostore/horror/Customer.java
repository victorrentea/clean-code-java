package videostore.horror;

import java.util.*;

import static java.util.stream.Collectors.joining;

class Customer {

   private final String name;
   private final List<Rental> rentals = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public List<Rental> getRentals() {
      return rentals;
   }

   public void addRental(Rental rental) {
      rentals.add(rental);
   }

   public String getName() {
      return name;
   }


}