package videostore.horror;

//@Service
//@RequiredArgsConstructor
//class SomeService {
//   private final SomeDep dep;
//
//
//}

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import videostore.horror.Movie.PriceCode;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
class PriceService {
   @Value("${child.price.factor}")
   int childPriceFactor;

   Map<PriceCode, Function<Integer, Double>> PRICE_FUNCTIONS = Map.of(
       PriceCode.NEW_RELEASE, this::computeNewReleasePrice,
       PriceCode.REGULAR, this::computeRegularPrice,
       PriceCode.CHILDREN, this::computeChildrenPrice
   );

   public Double computePrice(Rental rental) {
//      return PRICE_FUNCTIONS.getOrDefault(
//          rental.movie().priceCode(),
//            e -> { throw new RuntimeException("BOO");})
//      .apply(rental.daysRented());

      // java 17
//      return switch (rental.movie().priceCode()) {
//         case NEW_RELEASE -> computeNewReleasePrice(rental.daysRented());
//         case REGULAR -> computeRegularPrice(rental.daysRented());
//         case CHILDREN -> computeChildrenPrice(rental.daysRented());
//      };
      switch (rental.movie().priceCode()) {
         case NEW_RELEASE:
            return computeNewReleasePrice(rental.daysRented());
         case REGULAR:
            return computeRegularPrice(rental.daysRented());
         case CHILDREN:
            return computeChildrenPrice(rental.daysRented());
         default:
            throw new IllegalArgumentException();
      }


//      BiFunction<PriceService, Integer, Double> priceFunction =
//          rental.movie().priceCode().priceComputation;
//      return priceFunction.apply(this, rental.daysRented());

   }

   public double computeNewReleasePrice(int daysRented) {
      return daysRented * childPriceFactor;

   }
   public double computeRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2) {
         price += (daysRented - 2) * 1.5;
      }
      return price;
   }
   public double computeChildrenPrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3) {
         price += (daysRented - 3) * 1.5;
      }
      return price;
   }

}

public final class Rental {
   private final Movie movie;
   private final int daysRented;

   public Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public int daysRented() {
      return daysRented;
   }



   public double price() {
      // pretend to be spring
      PriceService service = new PriceService();
      service.childPriceFactor =3;
      return service.computePrice(this);

//      return movie.priceCode().price(daysRented);
//      double price = 0;
//      switch (movie.priceCode()) {
//         case REGULAR:
//            price += 2;
//            if (daysRented > 2) {
//               price += (daysRented - 2) * 1.5;
//            }
//            return price;
//         case NEW_RELEASE:
//            price += daysRented * 3;
//            return price;
//         case CHILDREN:
//            price += 1.5;
//            if (daysRented > 3) {
//               price += (daysRented - 3) * 1.5;
//            }
//            return price;
//         default:
//            throw new IllegalStateException("Unexpected value: " + movie.priceCode());
//      }
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
