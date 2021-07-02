package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Cacheable;
import java.util.Collections;
import java.util.Map;

class OrderLineParser {
    public static ParsedOrderLine  parse(String orderString) {
        String[] orderData = orderString.split("\\s+");
        String productCode = orderData[0].split("-")[1];
        int quantity = Integer.parseInt(orderData[1]);
        return new ParsedOrderLine(productCode, quantity);
    }
}
@RequiredArgsConstructor
public class SplitPhase {
    private final OrderLineParser orderLineParser;

    public float calculateOrderPrice(String orderString, Map<String, Integer> priceList) {
        // parse the line
        ParsedOrderLine line = OrderLineParser.parse(orderString);
/////---------------------------------------

//        Order order;
//        order.place();
        // compute price
        return logicaGreaDeTestatSiEaDarFaraParsare(priceList, line);
    }

    private int logicaGreaDeTestatSiEaDarFaraParsare(Map<String, Integer> priceList, ParsedOrderLine line) {
        Integer productPrice = priceList.get(line.getProductCode());
        return line.getQuantity() * productPrice;
    }

    public static void main(String[] args) {
//        System.out.println(new SplitPhase().calculateOrderPrice("Chair-CHR 4", Collections.singletonMap("CHR", 5)));
    }
}
//class OrderInformation
@Value
class ParsedOrderLine {
     String productCode;
     int quantity;
}
