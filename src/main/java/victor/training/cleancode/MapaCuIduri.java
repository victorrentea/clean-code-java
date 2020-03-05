package victor.training.cleancode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaCuIduri {
    public static void main(String[] args) {

        Map<Long, List<Long>> map = new HashMap<>();

        for (Long customerId : map.keySet()) {
            List<Long> orderIds = map.get(customerId);
        }

        // mai cinstit asa:
        List<CustomerOrderIds> orders = new ArrayList<>();
        for (CustomerOrderIds order : orders) {

        }
    }
}

class CustomerOrderIds {
    private final long customerId;
    private final List<Long> orderIds;

    CustomerOrderIds(long customerId, List<Long> orderIds) {
        this.customerId = customerId;
        this.orderIds = orderIds;
    }
}
