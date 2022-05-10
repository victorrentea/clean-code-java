package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.of(1,2,3), new Other(15));
      System.out.println(immutable);

//      numbers.clear(); //pb1
//      immutable.getNumbers().clear();//2
      // wilderness

      System.out.println(immutable);
   }
}

//record ImmutableJava17(int x, ImmutableList<Integer> numbers, Other other) {
//}
//@Value
class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }
   public int getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   @Override
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