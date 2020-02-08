package victor.training.refactoring;

import java.util.Map;

public class SplitPhase {
    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        String[] orderData = orderString.split("\\s+");
        Integer productPrice = priceList.get(orderData[0].split("-")[1]);
        int orderPrice = Integer.parseInt(orderData[1]) * productPrice;
        return orderPrice;
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Map.of("CHR", 5)));
    }
}
