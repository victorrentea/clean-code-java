package videostore.horror;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Category {
   CHILDREN(PriceService::computeChildrenPrice),
   REGULAR(PriceService::calculateRegularPrice),
   NEW_RELEASE(PriceService::computeNewReleasePrice),
   ;

   private final BiFunction<PriceService, Integer, Double> priceAlgo;

   private Category(BiFunction<PriceService, Integer, Double> priceAlgo) {

      this.priceAlgo = priceAlgo;
   }

   public BiFunction<PriceService, Integer, Double> getPriceAlgo() {
      return priceAlgo;
   }
}
