package videostore.horror;

import videostore.horror.Movie.Type;

// Polimorphism:
// abstract class Movie {
//   abstract double computePrice();
// }
// distrugem enum de tipuri (in practica va ramane ca pa o coloana)
// class NewReleaseMovie extends Movie {
//    double computePrice() { return daysRented * 3; }
// PLUS: cand adaugi un nou tip de film, codul nu va COMPILA pana nu implem metoda



public class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public double computePrice() {
      double price = 0;
      switch (movie.getType()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            break;
         case NEW_RELEASE:
            price += daysRented * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3)
               price += (daysRented - 3) * 1.5;
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + movie.getType());
      }
      return price;
   }

   public int computeRenterPoints() {
      int frequentRenterPoints = 1;
      boolean isNewRelease = movie.getType() == Type.NEW_RELEASE;
      if (isNewRelease && daysRented >= 2) {
         frequentRenterPoints += 1;
      }
      return frequentRenterPoints;
   }
}
