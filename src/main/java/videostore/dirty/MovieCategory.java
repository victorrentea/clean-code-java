package videostore.dirty;

import java.util.function.BiFunction;

public enum MovieCategory {
   CHILDREN,
   REGULAR,
   NEW_RELEASE,
   DE_BABACI;
//   CHILDREN(PriceService::calculateChildrenPrice),
//   REGULAR(PriceService::calculateRegularPrice),
//   NEW_RELEASE(PriceService::calculateNewReleasePrice);
//
//   private final BiFunction<PriceService, Integer, Double> priceAlgo;
//
//   MovieCategory(BiFunction<PriceService, Integer, Double> priceAlgo) {
//      this.priceAlgo = priceAlgo;
//   }
//
//   public double calculatePrice(PriceService priceService, int daysRented) {
//      return priceAlgo.apply(priceService, daysRented);
//   }
}
