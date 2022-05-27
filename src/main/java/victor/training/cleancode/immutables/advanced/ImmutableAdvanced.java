package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println(immutable);

//      numbers.add(-1);

      // wilderness
      f(immutable);

      System.out.println(immutable);
   }

   private static void f(Immutable immutable) {
      g(immutable);
   }

   private static void g(Immutable immutable) {
      immutable.numbers().add(1);
   }
}

record Immutable(int x, ImmutableList<Integer> numbers, Other other) {

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

//   public void setA(int a) {
//      this.a = a;
//   }
}
