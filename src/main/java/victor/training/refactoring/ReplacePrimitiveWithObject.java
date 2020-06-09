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
      Map<Long, List<Long>> idMap = customerRepo.getCustomerOrders();
      for (Long id : idMap.keySet()) {
         List<Long> ids = idMap.get(id);
         processCustomer(id, ids);
      }
   }

   private void processCustomer(Long id, List<Long> ids) {
      System.out.println("Process cid=" + id + " -> order Ids:" + ids);
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