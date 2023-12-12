package victor.training.cleancode.immutable.basic;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

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
   }
}

// The state of an immutable object cannot be changed after it is created.
@Value //?? = 'private final' fields + getters + equals/hashCode + toString
class Immutable { // acum acest obiect este // "deep immutable"
   Integer x;
   List<Integer> numbers;
   Other other;

   public List<Integer> getNumbers() {
      return new ArrayList<>(numbers); // #1 clona in getter: 1) misleading pt caller, 2) ineficient cu memoria
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
