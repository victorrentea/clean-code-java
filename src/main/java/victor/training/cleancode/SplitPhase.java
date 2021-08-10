package victor.training.cleancode;

import lombok.Value;

import java.util.Collections;
import java.util.Map;

@Value
class OrderLine {
    String productCode;
    int itemCount;
}
public class SplitPhase {
    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        // phase1: parse
        OrderLine orderLine = parseOrderString_phase1(orderString);

        // phase2: compute
        return computePrice_phase2(orderLine, priceList);
    }

    private int computePrice_phase2(OrderLine orderLine, Map<String, Integer> priceList) {
        Integer productPrice = priceList.get(orderLine.getProductCode());
        return orderLine.getItemCount() * productPrice;
    }

    private OrderLine parseOrderString_phase1(String orderString) {
        String[] orderData = orderString.split("\\s+");
        String productCode = orderData[0].split("-")[1];
        int itemCount = Integer.parseInt(orderData[1]);

        return new OrderLine(productCode, itemCount);
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4",
            Collections.singletonMap("CHR", 5)));
    }
}
