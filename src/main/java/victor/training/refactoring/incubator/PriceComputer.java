package victor.training.refactoring.incubator;

import java.util.Map;

public class PriceComputer {
   public PriceComputer() {
   }

   public int computePrice(OrderLine orderLine, Map<String, Integer> priceList) {
      Integer productPrice = priceList.get(orderLine.getProductCode());
      return orderLine.getItemCount() * productPrice;
   }
}