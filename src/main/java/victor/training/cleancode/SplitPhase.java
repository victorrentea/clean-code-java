package victor.training.cleancode;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;

public class SplitPhase {
    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        OrderLine orderLine = parse(orderString);
        return computePrice(orderLine, priceList);
    }

    private static int computePrice(OrderLine result, Map<String, Integer> priceList) {
        Integer productPrice = priceList.get(result.productCode());
        return result.quantity() * productPrice;
    }

    @NotNull
    private static OrderLine parse(String orderString) {
        String[] orderData = orderString.split("\\s+");
        String productCode = orderData[0].split("-")[1];
        int quantity = Integer.parseInt(orderData[1]);
        OrderLine result = new OrderLine(productCode, quantity);
        return result;
    }

    private record OrderLine(String productCode, int quantity) {
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4",
                Collections.singletonMap("CHR", 5)));
    }
}
