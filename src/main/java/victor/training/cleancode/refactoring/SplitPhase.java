package victor.training.cleancode.refactoring;

import java.util.Collections;
import java.util.Map;

public class SplitPhase {
  public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
    ParsedOrder order = ParsedOrder.parse(orderString);

    Integer productPrice = priceList.get(order.productCode());
    return order.items() * productPrice;
  }

  // level 1: push down the parsing logic (low level details)
  // level 2: split the class: extract the parsing logic into a separate class (adapter/mapper)
  //   DO NOT ENTER CORE LOGIC WITH DEGENERATE DATA. only with carefully designed structures

  private record ParsedOrder(String productCode, int items) {
    //in java you cannot write any statement before the this() call to the canonical constructor
//     public ParsedOrder(String orderString) { ugly
//      this(orderString.split("\\s+")[0].split("-")[1], Integer.parseInt(orderString.split("\\s+")[1]));
//    }
    public static ParsedOrder parse(String orderString) {
      String[] orderData = orderString.split("\\s+");
      String productCode = orderData[0].split("-")[1];
      int items = Integer.parseInt(orderData[1]);
      return new ParsedOrder(productCode, items);
    }
  }

  public static void main(String[] args) {
    System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
  }
}
