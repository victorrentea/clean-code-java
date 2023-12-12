package victor.training.cleancode.immutable.basic;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.Value;
import org.hibernate.collection.internal.PersistentBag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
          .collect(ImmutableList.toImmutableList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      darkBizRules(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable); // un obiect imutabil iti da siguranta ca nu se schimba pana aici
   }

   private static void darkBizRules(Immutable immutable) {
      // hack de 21:00 Vineri
//      immutable.getOther().setA(-9); // nu compileaza
      immutable.getNumbers().add(-1);
      immutable.getNumbers().clear();
   }
}

// The state of an immutable object cannot be changed after it is created.
@Value //?? = 'private final' fields + getters + equals/hashCode + toString
class Immutable { // acum acest obiect este // "deep immutable"
   Integer x;
   ImmutableList<Integer> numbers; // nu merge pe @Entity Hibernate. merge insa pe @Document mongo, DTO json jackson (Spring/JAVAEE)
   Other other;

//   List<Integer> list = new PersistentBag();

   public ImmutableList<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // #1 clona in getter: 1) misleading pt caller, 2) ineficient cu memoria
//      return Collections.unmodifiableList(numbers); // #2 Decorator™️ Pattern: un wrapper peste lista originala care arunca ex la orice modificare incerci
      return numbers; // #3 ImmutableList din guava library = Google Commons
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
