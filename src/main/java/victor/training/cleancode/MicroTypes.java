package victor.training.cleancode;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class MicroTypes {
    public void displayUrgent(List<Incident> incidents) {
        incidents.stream()
                // over 'normal'
                .filter(i-> i.getPriority().equals("high") || i.getPriority().equals("rush"))
                .forEach(System.out::println);
    }

    private CustomerRepo customerRepo = new CustomerRepo(); //pretend dep injection
    public void microIdTypes() {
        Map<CustomerId, List<OrderId>> map = customerRepo.getCustomerOrders();
        for (CustomerId id : map.keySet()) {
            List<OrderId> ids = map.get(id);
            processCustomer(id, ids);
        }
    }

    private void processCustomer(CustomerId id, List<OrderId> ids) {
        System.out.println("Process cid="+id + " -> order Ids:" + ids);
    }
}
@Value
class CustomerId {
    long id;
}
@Value
class OrderId {
    long id;
}

@Data
class Incident {
    private final Long id;
    private final String priority;
}

class CustomerRepo {

    Map<CustomerId, List<OrderId>> getCustomerOrders() {
        Map<CustomerId, List<OrderId>> map = new HashMap<>();
        // simulate loading data
        for (long i = 0; i < 10; i++) {
            long customerId = i;
            List<OrderId> orderIds = LongStream.range(0, i).boxed().map(OrderId::new).collect(toList());
            map.put(new CustomerId(customerId), orderIds);
        }
        return map;

    }
}