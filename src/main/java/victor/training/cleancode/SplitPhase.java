package victor.training.cleancode;

import java.util.Collections;
import java.util.Map;

public class SplitPhase {
    public float calculateOrderPrice(String order5String, Map<String, Integer> priceList) {
        String[] order5Data = order5String.split("\\s+");
        Integer productPrice = priceList.get(order5Data[0].split("-")[1]);
        return Integer.parseInt(order5Data[1]) * productPrice;
    }

    public static void main(String[] args) {
        SplitPhase splitPhase = new SplitPhase();
        System.out.println(splitPhase.calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
    }
}
