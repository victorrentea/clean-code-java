package videostore.horror;

import videostore.horror.Movie.Type;

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
      double totalPrice = 0;
      int frequentRenterPoints = 0;
      String result = "Rental Record for " + getName() + "\n";

      for (Rental rental : rentals) {
         Movie movie = rental.getMovie();
         int daysRented = rental.getDaysRented();

         // add frequent renter points
         frequentRenterPoints++;
         // add bonus for a two day new release rental
         boolean isNewRelease = movie.getType() == Type.NEW_RELEASE;
         if (isNewRelease && daysRented > 1)
            frequentRenterPoints++;

         double price = rental.determinePrice();
         // show figures line for this rental
         result += "\t" + movie.getTitle() + "\t" + price + "\n";
         totalPrice += price;
      }
      // add footer lines
      result += "Amount owed is " + totalPrice + "\n";
      result += "You earned " + frequentRenterPoints + " frequent renter points";
      return result;
   }

}