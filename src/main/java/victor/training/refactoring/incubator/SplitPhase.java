package victor.training.refactoring.incubator;

import java.util.Collections;
import java.util.Map;

public class SplitPhase {
    private final OrderLineParser orderLineParser = new OrderLineParser();
    private final PriceComputer priceComputer = new PriceComputer();

    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        OrderLine orderLine = orderLineParser.parse(orderString);
// line ------------------------------------------------------------
        return priceComputer.computePrice(orderLine, priceList);
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
    }
}

class OrderLine {
    private final String productCode;
    private final int itemCount;

    OrderLine(String productCode, int itemCount) {
        this.productCode = productCode;
        this.itemCount = itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getProductCode() {
        return productCode;
    }
}
