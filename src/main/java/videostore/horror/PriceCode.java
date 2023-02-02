package videostore.horror;

import java.util.function.BiFunction;

public enum PriceCode {
  REGULAR,
  NEW_RELEASE,
  CHILDREN,
//  ELDERS
}
//public enum PriceCode {
//  REGULAR(PriceCalculatorFormulas::computeRegularPrice),
//  NEW_RELEASE(PriceCalculatorFormulas::computeNewReleasePrice),
//  CHILDREN(PriceCalculatorFormulas::computeChildrenPrice);
//
//  public final BiFunction<PriceCalculatorFormulas, Integer, Double> priceFormula;
//
//  PriceCode(BiFunction<PriceCalculatorFormulas, Integer, Double> priceFormula) {
//    this.priceFormula = priceFormula;
//  }
//}