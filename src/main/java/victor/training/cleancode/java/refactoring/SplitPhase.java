package victor.training.cleancode.java.refactoring;

import java.util.Collections;
import java.util.Map;

public class SplitPhase {
    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        String[] orderData = orderString.split("\\s+");
        Integer productPrice = priceList.get(orderData[0].split("-")[1]);
        return Integer.parseInt(orderData[1]) * productPrice;
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
    }
}
