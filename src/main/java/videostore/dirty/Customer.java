package videostore.dirty;

import java.util.*;
import java.util.stream.Collectors;

class Customer {
   private final String name;
   private final List<Rental> rentals = new ArrayList<Rental>();

   public Customer(String name) {
      this.name = name;
   }

   public void addRental(Rental rental) {
      rentals.add(rental);
   }

   public String getName() {
      return name;
   }
public List<Rental> getRentals() {
	return rentals;
}
}