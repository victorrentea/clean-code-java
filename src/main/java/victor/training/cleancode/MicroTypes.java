package victor.training.cleancode;

import lombok.Data;

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

    private CustomerRepo customerRepo = new CustomerRepo(); //fake dep injection
    public void microIdTypes() {
        Map<Long, List<Long>> idMap = customerRepo.getCustomerOrders();
        for (Long id : idMap.keySet()) {
            List<Long> ids = idMap.get(id);
            processCustomer(id, ids);
        }
    }

    private void processCustomer(Long id, List<Long> ids) {
        System.out.println("Process cid="+id + " -> order Ids:" + ids);
    }
}

@Data
class Incident {
    private final Long id;
    private final String priority;
}

class CustomerRepo {

    Map<Long, List<Long>> getCustomerOrders() {
        Map<Long, List<Long>> map = new HashMap<>();
        // simulate loading data
        for (long i = 0; i < 10; i++) {
            long customerId = i;
            List<Long> orderIds = LongStream.range(0, i).boxed().collect(toList());
            map.put(customerId, orderIds);
        }
        return map;

    }
}