package videostore.dirty;

import java.util.function.BiFunction;

public enum MovieCategory {
   CHILDREN(ChildrenPriceCalculator.class),
   REGULAR(RegularPriceCalculator.class),
   NEW_RELEASE(NewReleasePriceCalculator.class);


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

   private final Class<? extends PriceCalculator> calculatorClass;

   MovieCategory(Class<? extends PriceCalculator> calculatorClass) {

      this.calculatorClass = calculatorClass;
   }

   public Class<? extends PriceCalculator> getCalculatorClass() {
      return calculatorClass;
   }
}


interface PriceCalculator {
   double computePrice(int daysRented);
}

class NewReleasePriceCalculator implements PriceCalculator {
   @Override
   public double computePrice(int daysRented) {
      return daysRented * 3;
   }
}
class ChildrenPriceCalculator implements PriceCalculator {
   @Override
   public double computePrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }
}
class RegularPriceCalculator implements PriceCalculator {
   @Override
   public double computePrice(int daysRented) {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
}