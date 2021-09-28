package victor.training.cleancode;

import lombok.Value;

import java.util.Collections;
import java.util.Map;

@Value
class ParsedOrderLine {
    String productCode;
    int itemCount;
}
public class SplitPhase {
    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        // parse Order String
        ParsedOrderLine line = parseOrderString(orderString);
        // ----------
        return determinePrice(priceList, line);
    }

    private ParsedOrderLine parseOrderString(String orderString) {
        String[] orderData = orderString.split("\\s+");
        String productCode = orderData[0].split("-")[1];
        int itemCount = Integer.parseInt(orderData[1]);
        return new ParsedOrderLine(productCode, itemCount);
    }

    private int determinePrice(Map<String, Integer> priceList, ParsedOrderLine line) {
        Integer productPrice = priceList.get(line.getProductCode());
        return line.getItemCount() * productPrice;
    }

    public static void main(String[] args) {
        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
    }
}
