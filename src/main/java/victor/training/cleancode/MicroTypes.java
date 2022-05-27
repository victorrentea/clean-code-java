package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

public class MicroTypes {
   private static void unknownFierceCode(Immutable obj) {
      // TODO what can go wrong here ?
   }

   public Map<CustomerId, List<ProductCount>> getCustomerIdToProductCounts() {
      CustomerId customerId = new CustomerId(1L);
      Integer product1Count = 2;
      Integer product2Count = 4;
      return Map.of(customerId, List.of(
              new ProductCount("Table", product1Count),
              new ProductCount("Chair", product2Count)
      ));
   }


   @Test
   void lackOfAbstractions() {
      Map<CustomerId, List<ProductCount>> map = getCustomerIdToProductCounts();
      // Joke: try "var" above :)
//log.debug("am uitat adnotarea!");
      for (CustomerId customerId : map.keySet()) {
         String pl = map.get(customerId).stream()
                 .map(t -> t.productName() + " of " + t.count())
                 .collect(joining(", "));
         System.out.println("cid=" + customerId + " got " + pl);
      }
   }

   @Test
   void immutables() {
      List<Integer> numbers = IntStream.range(1, 10).boxed().toList();
      Immutable obj = new Immutable("John", new Other("halo"), numbers);

      String original = obj.toString();
      System.out.println(obj);

      unknownFierceCode(obj);

      System.out.println(obj);

      assertThat(original).describedAs("State should not change!").isEqualTo(obj.toString());
   }

   record CustomerId(long id) {
   }// asta e nebun // microtipuri aka ID types
}

// -- RECORD --
// methods: add extra, overriding generated
// constructor:
// inheritance:


class Immutable {
   private final String name;
   private final Other other;
   private final List<Integer> list;

   public Immutable(String name, Other other, List<Integer> list) {
      this.name = name;
      this.other = other;
      this.list = list;
   }

   public String getName() {
      return name;
   }

   public Other getOther() {
      return other;
   }

   public List<Integer> getList() {
      return list;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Immutable immutable = (Immutable) o;
      return Objects.equals(name, immutable.name) && Objects.equals(other, immutable.other) && Objects.equals(list, immutable.list);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, other, list);
   }

   @Override
   public String toString() {
      return "Immutable{" +
              "name='" + name + '\'' +
              ", other=" + other +
              ", list=" + list +
              '}';
   }
}

class Other {
   private String data;

   public Other(String data) {
      this.data = data;
   }

   public String getData() {
      return data;
   }

   public Other setData(String data) {
      this.data = data;
      return this;
   }

   @Override
   public String toString() {
//      @Cleanup
      return "Other{" +
              "data='" + data + '\'' +
              '}';
   }
}


//@Value //= @Data + toate campurile priuvate finale by default
//class ProductCount {
//   String productName;
//   int count;
//}
record ProductCount(String productName, int count) {
}



