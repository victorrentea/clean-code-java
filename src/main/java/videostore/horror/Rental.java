package videostore.horror;

//@Service
//@RequiredArgsConstructor
//class SomeService {
//   private final SomeDep dep;
//
//
//}

import videostore.horror.Movie.PriceCode;

public final class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public double price() {
      double price = 0;
      switch (movie.priceCode()) {
         case REGULAR:
            price += 2;
            if (daysRented > 2) {
               price += (daysRented - 2) * 1.5;
            }
            return price;
         case NEW_RELEASE:
            price += daysRented * 3;
            return price;
         case CHILDREN:
            price += 1.5;
            if (daysRented > 3) {
               price += (daysRented - 3) * 1.5;
            }
            return price;
         default:
            throw new IllegalStateException("Unexpected value: " + movie.priceCode());
      }
   }




   public int frequentRenterPoints() {
      if (movie.isNewRelease() && daysRented >= 2) {
         return 2;
      } else {
         return 1;
      }
   }

   public Movie movie() {
      return movie;
   }
}
