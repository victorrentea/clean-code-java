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

   public void addRental(Movie movie, int daysRented) {
      rentals.add(new Rental(movie, daysRented));
   }

   public String getName() {
      return name;
   }


}