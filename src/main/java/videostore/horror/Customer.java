package videostore.horror;

import java.util.*;

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
      double totalAmount = 0;
      int frequentRenterPoints = 0;
      String result = "Rental Record for " + getName() + "\n";

      for (Rental rental : rentals) {
         Movie movie = rental.getMovie();
         int daysRented = rental.getDaysRented();

         // determine amounts for each line
         // add frequent renter points
         frequentRenterPoints++;
         // add bonus for a two day new release rental
         if (movie.getType() != null &&
             (movie.getType() == Movie.Type.NEW_RELEASE)
             && daysRented > 1)
            frequentRenterPoints++;
         
         
         double thisAmount = determineAmount(rental);
         // show figures line for this rental
         result += "\t" + movie.getTitle() + "\t"
                   + String.valueOf(thisAmount) + "\n";
         totalAmount += thisAmount;
      }
      // add footer lines
      result += "Amount owed is " + totalAmount + "\n";
      result += "You earned " + frequentRenterPoints + " frequent renter points";
      return result;
   }

   private double determineAmount(Rental rental) {
      int daysRented = rental.getDaysRented();
      Movie movie = rental.getMovie();

      double thisAmount = 0;
      switch (movie.getType()) {
         case REGULAR:
            thisAmount += 2;
            if (daysRented > 2)
               thisAmount += (daysRented - 2) * 1.5;
            break;
         case NEW_RELEASE:
            thisAmount += daysRented * 3;
            break;
         case CHILDREN:
            thisAmount += 1.5;
            if (daysRented > 3)
               thisAmount += (daysRented - 3) * 1.5;
            break;
      }
      return thisAmount;
   }
}