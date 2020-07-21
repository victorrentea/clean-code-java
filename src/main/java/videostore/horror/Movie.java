package videostore.horror;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.function.BiFunction;

class VreunRepo {}

@Service
class CalculatePriceService {
   @Autowired
   private VreunRepo repo;

   public double determinePrice(Movie.Category category, int daysRented) {
      return category.priceAlgo.apply(this, daysRented);
   }

   public double determineChildrenPrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }
   public double determineRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
   public double determineNewReleasePrice(int daysRented) {
      return  daysRented * 3;
   }
}


public class Movie {
   enum Category {
      CHILDREN(CalculatePriceService::determineChildrenPrice),
      REGULAR(CalculatePriceService::determineRegularPrice),
      NEW_RELEASE(CalculatePriceService::determineNewReleasePrice);
      public final BiFunction<CalculatePriceService, Integer, Double> priceAlgo;

      Category(BiFunction<CalculatePriceService, Integer, Double> priceAlgo) {
         this.priceAlgo = priceAlgo;
      }
   }
   private final String title;
   private final Category category;

   public Movie(String title, Category category) {
      this.title = title;
      this.category = category;
   }

   public Category getCategory() {
      return category;
   }

   public boolean isNewRelease() {
      return getCategory() == Category.NEW_RELEASE;
   }

   public String getTitle() {
      return title;
   }

}
