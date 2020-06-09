package victor.training.refactoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;
import static victor.training.refactoring.Incident.Priority.normal;

public class ReplacePrimitiveWithObject {
   public void displayUrgent(List<Incident> incidents) {
      incidents.stream()
          // over 'normal'
          .filter(Incident::aboveNormal)
          .forEach(System.out::println);
   }

   private CustomerRepo customerRepo = new CustomerRepo(); //fake dep injection

   public void microIdTypes() {
      Map<Long, List<Long>> customerIdToOrderIds = customerRepo.getCustomerOrders();
//      step1: Map<CustomerId, List<OrderId>> orders = customerRepo.getCustomerOrders();
//      List<CustomerOrderIds> customerIdToOrderIds = customerRepo.getCustomerOrders();

      // Step2:
      // cand iterezi pe cheile mapei si faci imediat get(key), de fapt tu iti bati joc de mapa
      // o folosesti intr-un mod degenerat ca o simpla lista.
      for (Long customerId : customerIdToOrderIds.keySet()) {
         List<Long> orderIds = customerIdToOrderIds.get(customerId);

//      for (CustomerOrderIds co : list) {
         processCustomer(customerId, orderIds);
      }
   }

   private void processCustomer(Long customerIds, List<Long> orderIds) {
      System.out.println("Process cid=" + customerIds + " -> order Ids:" + orderIds);
   }
}

class CustomerOrderIds {
   private final long customerId;
   private final List<Long> orderIds;

   CustomerOrderIds(long customerId, List<Long> orderIds) {
      this.customerId = customerId;
      this.orderIds = orderIds;
   }

   public List<Long> getOrderIds() {
      return orderIds;
   }

   public long getCustomerId() {
      return customerId;
   }
}


// microtypes
class CustomerId {
   private final long id;
   CustomerId(long id) {
      this.id = id;
   }
   public long getId() {
      return id;
   }
}

class Incident {
   enum Priority {
      low(1),
      normal(2),
      high(3),
      rush(4);

      private int value;

      Priority(int value) {
         this.value = value;
      }

      public boolean above(Priority other) {
         return this.value > other.value; // periculos, daca cineva reordoneaza intrarile din enum ALFABETIC
//            return this.ordinal() > normal.ordinal(); // periculos, daca cineva reordoneaza intrarile din enum ALFABETIC
//            return this == high || this == rush;
      }

   }
   private final Long id;

   private final Priority priority; // low normal high rush
   Incident(Long id, Priority priority) {
      this.id = id;
      this.priority = priority;
   }

   boolean aboveNormal() {
      return priority.above(normal);
   }

   public Long getId() {
      return id;
   }

   public Priority getPriority() {
      return priority;
   }
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